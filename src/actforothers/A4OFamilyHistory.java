package actforothers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class A4OFamilyHistory extends ONCObject implements Serializable
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
	String changedBy;
	Calendar timestamp;

	//Constructor used after separating Family History from ONC Families
	public A4OFamilyHistory(int id, int famid, FamilyStatus fStat, FamilyGiftStatus dStat, int partnerID, 
							String notes, String cb, Calendar dateChanged)
	{
		super(id);
		this.famID = famid;
		this.familyStatus = fStat;
		this.giftStatus = dStat;			
		this.partnerID = partnerID;
		this.dNotes = notes;		
		this.changedBy = cb;
		this.timestamp = Calendar.getInstance();
		this.timestamp = dateChanged;
	}
		
	//Copy Constructor
	public A4OFamilyHistory(A4OFamilyHistory d)
	{	
		super(d.id);
		famID = d.famID;
		familyStatus = d.familyStatus;
		giftStatus = d.giftStatus;			
		this.partnerID = d.partnerID;
		dNotes = d.dNotes;		
		changedBy = d.changedBy;
		timestamp = Calendar.getInstance();
	}
	
	//Constructor used when reading from Family History .csv file
	public A4OFamilyHistory(String[] del)
	{
		super(Integer.parseInt(del[0]));
		famID = Integer.parseInt(del[1]);
		familyStatus = FamilyStatus.getFamilyStatus(Integer.parseInt(del[2]));
		giftStatus = FamilyGiftStatus.getFamilyGiftStatus(Integer.parseInt(del[3]));
		partnerID = -1;
		if(!del[4].isEmpty() && isNumeric(del[4]))
			partnerID = Integer.parseInt(del[4]);
		dNotes = del[5].isEmpty() ? "" : del[5];	
		changedBy = del[6].isEmpty() ? "" : del[6];
		timestamp = Calendar.getInstance();
		timestamp.setTimeInMillis(Long.parseLong(del[7]));
	}

	//Getters
	public int getFamID() { return famID; }
	public FamilyStatus getFamilyStatus() {return familyStatus;}
	public FamilyGiftStatus getGiftStatus() {return giftStatus;}	
	public int getPartnerID() {return partnerID;}
	String getNotes() {return dNotes;}
	public String getChangedBy() { return changedBy; }
	public Date getTimestamp() { return timestamp.getTime(); }
	
	//Setters
	public void setPartnerID(int id) { partnerID = id; }
	public void setFamilyGiftStatus(FamilyGiftStatus giftStatus) { this.giftStatus = giftStatus; }
	void setNotes(String s) {dNotes = s; }
	public void setChangedBy(String cb) { changedBy = cb; }	
	void setDateChanged(Date d) { timestamp.setTime(d); }
	public void setDateChanged(Calendar calDateChanged) { timestamp = calDateChanged; }
	
	@Override
	public String[] getExportRow()
	{
		String[] exportRow = {Integer.toString(id), Integer.toString(famID),
							  Integer.toString(familyStatus.statusIndex()),
							  Integer.toString(giftStatus.statusIndex()),
							  Integer.toString(partnerID),
							  dNotes, changedBy, Long.toString(timestamp.getTimeInMillis())};
		
		return exportRow;
		
	}
}