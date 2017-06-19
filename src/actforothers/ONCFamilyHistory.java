package actforothers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ONCFamilyHistory extends ONCObject implements Serializable
{
	/**
	 * This class implements the data structure for Family History objects. When an ONC Family objects 
	 * FamilyStatus or FamilyGift Status changes, this object is created and stored to archive the change
	 */
	private static final long serialVersionUID = 5109480607565108347L;
	
	int famID;
	FamilyStatus familyStatus;
	FamilyGiftStatus giftStatus;
	int partnerID;
	String dNotes;
	String dChangedBy;
	Calendar dDateChanged;

	//Constructor used after separating Family History from ONC Families
	public ONCFamilyHistory(int id, int famid, FamilyStatus fStat, FamilyGiftStatus dStat, int partnerID, 
							String notes, String cb, Calendar dateChanged)
	{
		super(id);
		this.famID = famid;
		this.familyStatus = fStat;
		this.giftStatus = dStat;			
		this.partnerID = partnerID;
		this.dNotes = notes;		
		this.dChangedBy = cb;
		this.dDateChanged = Calendar.getInstance();
		this.dDateChanged = dateChanged;
	}
		
	//Copy Constructor
	public ONCFamilyHistory(ONCFamilyHistory d)
	{	
		super(d.id);
		famID = d.famID;
		familyStatus = d.familyStatus;
		giftStatus = d.giftStatus;			
		this.partnerID = d.partnerID;
		dNotes = d.dNotes;		
		dChangedBy = d.dChangedBy;
		dDateChanged = Calendar.getInstance();
	}
	
	//Constructor used when reading from Family History .csv file
	public ONCFamilyHistory(String[] del)
	{
		super(Integer.parseInt(del[0]));
		famID = Integer.parseInt(del[1]);
		familyStatus = FamilyStatus.getFamilyStatus(Integer.parseInt(del[2]));
		giftStatus = FamilyGiftStatus.getFamilyGiftStatus(Integer.parseInt(del[3]));
		partnerID = -1;
		if(!del[4].isEmpty() && isNumeric(del[4]))
			partnerID = Integer.parseInt(del[4]);
		dNotes = del[5].isEmpty() ? "" : del[5];	
		dChangedBy = del[6].isEmpty() ? "" : del[6];
		dDateChanged = Calendar.getInstance();
		dDateChanged.setTimeInMillis(Long.parseLong(del[7]));
	}

	//Getters
	public int getFamID() { return famID; }
	public FamilyStatus getFamilyStatus() {return familyStatus;}
	public FamilyGiftStatus getGiftStatus() {return giftStatus;}	
	public int getPartnerID() {return partnerID;}
	String getdNotes() {return dNotes;}
	String getdChangedBy() { return dChangedBy; }
	public Date getdChanged() { return dDateChanged.getTime(); }
	
	//Setters
	public void setPartnerID(int id) { partnerID = id; }
	void setdNotes(String s) {dNotes = s; }
	void setdChangedBy(String cb) { dChangedBy = cb; }	
	void setDateChanged(Date d) { dDateChanged.setTime(d); }
	public void setDateChanged(Calendar calDateChanged) { dDateChanged = calDateChanged; }
	
	@Override
	public String[] getExportRow()
	{
		String[] exportRow = {Integer.toString(id), Integer.toString(famID),
							  Integer.toString(familyStatus.statusIndex()),
							  Integer.toString(giftStatus.statusIndex()),
							  Integer.toString(partnerID),
							  dNotes, dChangedBy, Long.toString(dDateChanged.getTimeInMillis())};
		
		return exportRow;
		
	}
}