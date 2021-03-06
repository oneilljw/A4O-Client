package actforothers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.google.gson.Gson;

public class FamilyHistoryDialog extends HistoryDialog 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int FAMILY_STATUS_COL = 0;
	private static final int GIFT_STATUS_COL = 1;
	private static final int DELIVERED_BY_COL = 2;
	private static final int NOTES_COL = 3;
	private static final int CHANGED_BY_COL = 4;
	private static final int DATE_CHANGED_COL = 5;
	
	private AbstractTableModel dlgTableModel;
	
	private FamilyHistoryDB familyHistoryDB;
	private VolunteerDB volunteerDB;
	private PartnerDB partnerDB;
	
	private List<A4OFamilyHistory> histList;
	
	public FamilyHistoryDialog(JFrame pf) 
	{
		super(pf, "& Gift Status");
		btnDelete.setVisible(false); //can't delete a family history object
		
		volunteerDB = VolunteerDB.getInstance();
		partnerDB = PartnerDB.getInstance();
		familyHistoryDB = FamilyHistoryDB.getInstance();
		
		if(volunteerDB != null)
			volunteerDB.addDatabaseListener(this);
		
		if(familyHistoryDB != null)
			familyHistoryDB.addDatabaseListener(this);
		
		histList = new ArrayList<A4OFamilyHistory>();
	}
	
	@Override
	void display(ONCObject obj) 
	{
		this.currFam = (A4OFamily) obj;
		setDialogTitle();
		histList = getSortedList();
		dlgTableModel.fireTableDataChanged();
	}
	
	List<A4OFamilyHistory> getSortedList()
	{
		List<A4OFamilyHistory> hList = familyHistoryDB.getDeliveryHistoryAL(currFam.getID());
		Collections.sort(hList, new HistoryItemDateChangedComparator());
		
		return hList;
	}

	@Override
	void delete()
	{
		// TODO Auto-generated method stub
	}

	
	/************************************************************************************************
	 * Updates the data base for changes made to family delivery data in the table. Currently, only
	 * delivery notes can be modified from the table. To update, method creates a copy of the
	 * current delivery object with the user requested notes field change and sends it to the
	 * local delivery data base. If an affirmative change response is received, the method updates
	 * the list that holds table data. 
	 * @param row
	 * @param col
	 ***********************************************************************************************/
	void updateFamilyHistoryNote(String notes)
	{
		//If it exists, get the ONC Delivery object and compare it to the data in the cell that changed
		//Store new data back into the sub database as necessary and indicate the data base changed				      
		A4OFamilyHistory updateDelReq = new A4OFamilyHistory(histList.get(0));	//make a copy 
			
		//Update the notes and changed by fields in the request
		updateDelReq.setNotes(notes);
		updateDelReq.setChangedBy(userDB.getUserLNFI());
			
		//send the request to the local data base
		String response = familyHistoryDB.update(this, updateDelReq);	
		if(response.startsWith("UPDATED_DELIVERY"))	//did local data base update?
		{
			Gson gson = new Gson();
			A4OFamilyHistory updatedDel = gson.fromJson(response.substring(16), A4OFamilyHistory.class);
			histList.set(0, updatedDel);
		}
		else
		{
			//display an error message that update request failed
			GlobalVariables gvs = GlobalVariables.getInstance();
			JOptionPane.showMessageDialog(this, "ONC Server denied History Update," +
					"try again later","Delivery Update Failed", JOptionPane.ERROR_MESSAGE, gvs.getImageIcon(0));
		}
			
		display(currFam);				
	}
	
	@Override
	public void dataChanged(DatabaseEvent dbe)
	{
		if(dbe.getSource() != this && this.isVisible() && dbe.getType().equals("UPDATED_DELIVERY") ||
			dbe.getType().equals("ADDED_DELIVERY"))
		{
			A4OFamilyHistory updatedHistoryObj = (A4OFamilyHistory) dbe.getObject1();
			
			//If updated delivery belongs to family delivery history being displayed,
			//re-display it
			if(currFam != null && currFam.getID() == updatedHistoryObj.getFamID())
				display(currFam);
		}
		else if(dbe.getSource() != this && this.isVisible() && dbe.getType().equals("ADDED_DRIVER"))
		{
			//driver may have been added to current family delivery history
			if(currFam != null)
				display(currFam);
		}
	}

	@Override
	AbstractTableModel createTableModel()
	{
		dlgTableModel = new DialogTableModel();
		return dlgTableModel;
	}

	@Override
	String[] getColumnToolTips() 
	{
		String[] colTT = {"Family Status", "Gift Status", "Who delivered gifts to the family", "Notes", "Changed By", "Time Stamp"};
		return colTT;
	}

	@Override
	int[] getColumnWidths()
	{
		int[] colWidths = {88, 88, 144, 176, 96, 128};
		return colWidths;
	}
	
	class DialogTableModel extends AbstractTableModel
	{
        /**
		 * Implements the table model for the Delivery History Dialog
		 */
		private static final long serialVersionUID = 1L;
		private String[] columnNames = {"Fam Status", "Gift Status", "Gifts Referred To", "Notes", "Changed By", "Time Stamp"};
		private SimpleDateFormat sdf;
		
		public DialogTableModel()
		{
			sdf = new SimpleDateFormat("M/dd/yy H:mm");
		}

        public int getColumnCount() { return columnNames.length; }
 
        public int getRowCount() { return histList == null ? 0 : histList.size(); }
 
        public String getColumnName(int col) { return columnNames[col]; }
 
        public Object getValueAt(int row, int col)
        {
        	Object value;
        	
        	A4OFamilyHistory histObj = histList.get(row);
        	
        	if(col ==  FAMILY_STATUS_COL)
        		value = histObj.getFamilyStatus().toString();
        	else if(col == GIFT_STATUS_COL)
        		value = histObj.getGiftStatus().toString();
        	else if(col == DELIVERED_BY_COL)
        	{
        		if(histObj.getPartnerID() == -1)
        			value = "N/A";
        		else
        			value = partnerDB.getPartnerByID(histObj.getPartnerID()).getName();
        	}
        	else if(col == NOTES_COL)
        		value = histObj.getNotes();
        	else if(col == CHANGED_BY_COL)
        		value = histObj.getChangedBy();
        	else if(col == DATE_CHANGED_COL)
        		value = sdf.format(histObj.getTimestamp());
        	else
        		value = "Error";
        	
         	return value;
        }
        
        //JTable uses this method to determine the default renderer/editor for each cell.
        @Override
        public Class<?> getColumnClass(int column)
        {
        	return String.class;
        }
 
        public boolean isCellEditable(int row, int col)
        {
        	return row == 0 && col == NOTES_COL;
        }

        public void setValueAt(Object value, int row, int col)
        { 
        	//verify user changed the delivery not and if so,
        	//update the delivery and redisplay. Table row 0 always
        	//contains the most recent delivery
        	if(!histList.isEmpty() && row == 0 && col == NOTES_COL)
        	{
        		String notes = (String) value;
        		if(!notes.equals(histList.get(0).getNotes()))
        			updateFamilyHistoryNote(notes);
        	}
        }
    }
	
	private class HistoryItemDateChangedComparator implements Comparator<A4OFamilyHistory>
	{
		@Override
		public int compare(A4OFamilyHistory o1, A4OFamilyHistory o2)
		{
			return o2.getTimestamp().compareTo(o1.getTimestamp());
		}
	}
}
