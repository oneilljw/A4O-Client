package actforothers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import au.com.bytecode.opencsv.CSVWriter;

public class FamilyHistoryDB extends ONCDatabase
{
	private static FamilyHistoryDB instance = null;
	private List<A4OFamilyHistory> fhAL;
	
	private FamilyHistoryDB()
	{
		super();
		fhAL = new ArrayList<A4OFamilyHistory>();
	}
	
	public static FamilyHistoryDB getInstance()
	{
		if(instance == null)
			instance = new FamilyHistoryDB();
		
		return instance;
	}

	String add(Object source, ONCObject entity)
	{
		Gson gson = new Gson();
		String response = "";
		
		response = serverIF.sendRequest("POST<add_delivery>" + 
											gson.toJson(entity, A4OFamilyHistory.class));
		if(response.startsWith("ADDED_DELIVERY"))
			processAddedObject(source, response.substring(14));
		
		return response;	
	}
	
	
	void processAddedObject(Object source, String json)
	{
		//Store added ONCFamilyHistory object in local data base
		Gson gson = new Gson();
		A4OFamilyHistory addedObject = gson.fromJson(json, A4OFamilyHistory.class);
		
		fhAL.add(addedObject);
/*	
		//update the family status this is a delivery is associated with
		FamilyDB familyDB = FamilyDB.getInstance();
		ONCFamily fam = familyDB.getFamily(addedObject.getFamID());
		fam.setFamilyStatus(addedObject.getFamilyStatus());
		fam.setGiftStatus(addedObject.getGiftStatus());
		fam.setDeliveryID(addedObject.getID());
*/		
		//Notify local user IFs that an organization/partner was added. This will
		//be all UI's that display or manage family delivery information
		fireDataChanged(source, "ADDED_DELIVERY", addedObject);
	}
	
	A4OFamilyHistory getFamilyHistory(int id)
	{
		int index = 0;
		while(index < fhAL.size() && fhAL.get(index).getID() != id)
			index++;
		
		if(index == fhAL.size())
			return null;
		else
			return fhAL.get(index);
	}
	
	ArrayList<A4OFamilyHistory> getDeliveryHistoryAL(int famID)
	{
		ArrayList<A4OFamilyHistory> famDelAL = new ArrayList<A4OFamilyHistory>();
		for(A4OFamilyHistory d:fhAL)
			if(d.getFamID() == famID)
				famDelAL.add(d);
		return famDelAL;
	}
	
	/**************************************************************************************************
	 * Return the partner field in the most recent history object for the family
	 * @param famID
	 * @return
	 */
	int getPartnerID(int delID)
	{
		int index = 0;
		while(index < fhAL.size() && fhAL.get(index).getID() != delID)
			index++;
				
		if(index==fhAL.size())
			return -1;
		else
			return fhAL.get(index).getPartnerID();
	}
	
	String importFamilyHistoryDatabase()
	{
		String response = "NO_DELIVERIES";
		
		if(serverIF != null && serverIF.isConnected())
		{		
			Gson gson = new Gson();
			Type listtype = new TypeToken<ArrayList<A4OFamilyHistory>>(){}.getType();
			
			response = serverIF.sendRequest("GET<deliveries>");
				fhAL = gson.fromJson(response, listtype);
				
			if(!response.startsWith("NO_DELIVIERIES"))		
				response =  "DELIVERIES_LOADED";
		}
		
		return response;
	}
	
	String exportDBToCSV(JFrame pf, String filename)
    {
		File oncwritefile = null;
		
    	if(filename == null)
    	{
    		ONCFileChooser fc = new ONCFileChooser(pf);
    		oncwritefile= fc.getFile("Select .csv file to save Delivery DB to",
										new FileNameExtensionFilter("CSV Files", "csv"), 1);
    	}
    	else
    		oncwritefile = new File(filename);
    	
    	if(oncwritefile!= null)
    	{
    		//If user types a new filename and doesn't include the .csv, add it
	    	String filePath = oncwritefile.getPath();		
	    	if(!filePath.toLowerCase().endsWith(".csv")) 
	    		oncwritefile = new File(filePath + ".csv");
	    	
	    	try 
	    	{
	    		 String[] header = {"Delivery ID", "Family ID", "Status", "Del By", 
	    				 			"Notes", "Changed By", "Time Stamp"};
	    		
	    		CSVWriter writer = new CSVWriter(new FileWriter(oncwritefile.getAbsoluteFile()));
	    	    writer.writeNext(header);
	    	    
	    	    for(A4OFamilyHistory d:fhAL)
	    	    	writer.writeNext(d.getExportRow());	//Get family data
	    	 
	    	    writer.close();
	    	    filename = oncwritefile.getName();
	    	       	    
	    	} 
	    	catch (IOException x)
	    	{
	    		System.err.format("IO Exception: %s%n", x);
	    		JOptionPane.showMessageDialog(pf, oncwritefile.getName() + " could not be saved", 
						"ONC File Save Error", JOptionPane.ERROR_MESSAGE);
	    	}
	    }
    	
	    return filename;
    }

	@Override
	public void dataChanged(ServerEvent ue) 
	{
		if(ue.getType().equals("ADDED_DELIVERY"))
		{
			processAddedObject(this, ue.getJson());
		}
		else if(ue.getType().equals("UPDATED_DELIVERY"))
		{
			processUpdatedObject(this, ue.getJson());
		}
	}

	@Override

	/***************************************************************
	 * This method is called when a family history object has been updated by 
	 * the user. The request update object is passed. 
	 *************************************************************/
	String update(Object source, ONCObject updatedHistory)
	{
		//notify the server
		Gson gson = new Gson();
		String response = null;
		
		response = serverIF.sendRequest("POST<update_delivery>" + 
												gson.toJson(updatedHistory, A4OFamilyHistory.class));
			 
		
		//check response. If response from server indicates a successful update,
		//create and store the updated object in the local data base and notify local
		//ui listeners of a change. The server may have updated the prior year ID
		if(response.startsWith("UPDATED_DELIVERY"))
			processUpdatedObject(source, response.substring(16));
		
		return response;
	}
	
	void processUpdatedObject(Object source, String json)
	{
		//Create a new object for the update
		Gson gson = new Gson();
		A4OFamilyHistory updatedObj = gson.fromJson(json, A4OFamilyHistory.class);
		
		//Find the position for the history object being updated
		int index = 0;
		while(index < fhAL.size() && fhAL.get(index).getID() != updatedObj.getID())
			index++;
		
		//Replace the current history object with the update
		if(index < fhAL.size())
		{
			fhAL.set(index, updatedObj);
			fireDataChanged(source, "UPDATED_DELIVERY", updatedObj);
		}
		else
			System.out.println(String.format("DeliveryDB processUpdatedObject - delivery id %d not found",
					updatedObj.getID()));
	}
}
