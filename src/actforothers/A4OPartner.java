package actforothers;

import java.util.Date;

public class A4OPartner extends ONCEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7854045836478089523L;
	
	private int status;
	private PartnerType type;
	private GiftCollection collection;
	private String name;
//	private String ornamentDelivery;
	private int streetnum;
	private String streetname;
	private String unit;
	private String city;
	private String zipcode;
	private int region;
	private String phone;
	private int gift_families_req;
	private int gift_families_assigned;
	private int orn_delivered;
	private int orn_rec_before;
	private int orn_rec_after;
	private String other;
	private String confirmed;
	private String deliverTo;
	private String specialNotes;
	private String contact;
	private String contact_email;
	private String contact_phone;
	private String contact2;
	private String contact2_email;
	private String contact2_phone;
	private int pyRequested;
	private int pyAssigned;
	private int pyDelivered;
	private int pyReceivedBeforeDeadline;
	private int pyReceivedAfterDeadline;
	
	A4OPartner(int orgid, String createdBy)
	{
		//constructor used when adding a new organization
		super(orgid, new Date(), createdBy, STOPLIGHT_OFF, "Partner created", createdBy);
		status = 0;
		type = PartnerType.Unknown;
		collection = GiftCollection.Unknown;
		name = "";
//		ornamentDelivery = "";
		streetnum = 0;
		streetname = "";
		unit = "";
		city = "";
		zipcode = "";
		region = 0;
		phone = "";
		gift_families_req = 0;
		gift_families_assigned = 0;
		orn_delivered = 0;
		orn_rec_before = 0;
		orn_rec_after = 0;
		other = "";
		confirmed = "";
		deliverTo = "";
		specialNotes = "";
		contact = "";
		contact_email = "";
		contact_phone = "";
		contact2 = "";
		contact2_email = "";
		contact2_phone = "";
		pyRequested = 0;
		pyAssigned = 0;
		pyDelivered = 0;
		pyReceivedBeforeDeadline = 0;
		pyReceivedAfterDeadline = 0;
	}
	
	A4OPartner(int orgid, String name, String createdBy)
	{
		//constructor used when adding creating a non-assigned organization for wish filter and 
		//selection lists
		super(orgid, new Date(), createdBy, STOPLIGHT_RED, "Non-Assigned Partner created", createdBy);
		status = 0;
		type = PartnerType.Unknown;
		collection = GiftCollection.Unknown;
		this.name = name;
//		ornamentDelivery = "";
		streetnum = 0;
		streetname = "";
		unit = "";
		city = "";
		zipcode = "";
		region = 0;
		phone = "";
		gift_families_req = 0;
		gift_families_assigned = 0;
		orn_delivered = 0;
		orn_rec_before = 0;
		orn_rec_after = 0;
		other = "";
		confirmed = "";
		deliverTo = "";
		specialNotes = "";
		contact = "";
		contact_email = "";
		contact_phone = "";
		contact2 = "";
		contact2_email = "";
		contact2_phone = "";
		pyRequested = 0;
		pyAssigned = 0;
		pyDelivered = 0;
		pyReceivedBeforeDeadline = 0;
		pyReceivedAfterDeadline = 0;
	}
	
	A4OPartner(int orgid, Date date, String changedBy, int slPos, String slMssg, String slChangedBy,
			int status, PartnerType type, GiftCollection collection, String name, int streetnum, String streetname,
			String unit, String city, String zipcode, String phone, int orn_req, String other, 
			String deliverTo, String specialNotes, String contact, String contact_email,
			String contact_phone, String contact2, String contact2_email, String contact2_phone)
	{
		//constructor used when adding a new organization
		super(orgid, date, changedBy, STOPLIGHT_OFF, slMssg, slChangedBy);
		this.status = status;
		this.type = type;
		this.collection = collection;
		this.name = name;
//		this.ornamentDelivery = ornDelivery;
		this.streetnum = streetnum;
		this.streetname = streetname;
		this.unit = unit;
		this.city = city;
		this.zipcode = zipcode;
		this.region = 0;
		this.phone = phone;
		this.gift_families_req = orn_req;
		this.gift_families_assigned = 0;
		this.orn_delivered = 0;
		this.orn_rec_before = 0;
		this.orn_rec_after = 0;
		this.other = other;
		this.confirmed = "";
		this.deliverTo = deliverTo;
		this.specialNotes = specialNotes;
		this.contact = contact;
		this.contact_email = contact_email;
		this.contact_phone = contact_phone;
		this.contact2 = contact2;
		this.contact2_email = contact2_email;
		this.contact2_phone = contact2_phone;
		this.pyRequested = 0;
		this.pyAssigned = 0;
		this.pyDelivered = 0;
		this.pyReceivedBeforeDeadline = 0;
		this.pyReceivedAfterDeadline = 0;
	}
	
	//copy constructor - makes a copy of the partner
	public A4OPartner(A4OPartner o)
	{
		super(o.getID(), o.getDateChanged(), o.getChangedBy(), o.getStoplightPos(),
				o.getStoplightMssg(), o.getStoplightChangedBy());
		this.status = o.status;
		this.type = o.type;
		this.collection = o.collection;
		this.name = o.name;
//		this.ornamentDelivery = o.ornamentDelivery;
		this.streetnum = o.streetnum;
		this.streetname = o.streetname;
		this.unit = o.unit;
		this.city = o.city;
		this.zipcode = o.zipcode;
		this.region = o.region;
		this.phone = o.phone;
		this.gift_families_req = o.gift_families_req;
		this.gift_families_assigned = o.gift_families_assigned;
		this.orn_delivered = o.orn_delivered;
		this.orn_rec_before = o.orn_rec_before;
		this.orn_rec_after = o.orn_rec_after;
		this.other = o.other;
		this.confirmed = "";
		this.deliverTo = o.deliverTo;
		this.specialNotes = o.specialNotes;
		this.contact = o.contact;
		this.contact_email = o.contact_email;
		this.contact_phone = o.contact_phone;
		this.contact2 = o.contact2;
		this.contact2_email = o.contact2_email;
		this.contact2_phone = o.contact2_phone;
		this.pyRequested = o.pyRequested;
		this.pyAssigned = o.pyAssigned;
		this.pyDelivered = o.pyDelivered;
		this.pyReceivedBeforeDeadline = o.pyReceivedBeforeDeadline;
		this.pyReceivedAfterDeadline = o.pyReceivedAfterDeadline;
	}
	
	//Constructor for import from .csv
	public A4OPartner(String[] nextLine)	
	{
		super(Integer.parseInt(nextLine[0]), Long.parseLong(nextLine[26]), nextLine[27],
				Integer.parseInt(nextLine[28]), nextLine[29], nextLine[30]);
		status = Integer.parseInt(nextLine[1]);
		type = PartnerType.getPartnerType(Integer.parseInt(nextLine[2]));
//		type = Integer.parseInt(nextLine[2]);
		collection = nextLine[3].isEmpty() ? GiftCollection.Unknown : GiftCollection.valueOf(nextLine[3]);
		name = getDBString(nextLine[4]);
//		ornamentDelivery = getDBString(nextLine[5]);
		streetnum = nextLine[5].isEmpty() ? 0 : Integer.parseInt(nextLine[5]);
		streetname = getDBString(nextLine[6]);
		unit = getDBString(nextLine[7]);
		city =getDBString(nextLine[8]);
		zipcode = getDBString(nextLine[9]);
		region = nextLine[10].isEmpty() ? 0 : Integer.parseInt(nextLine[10]);
		phone = getDBString(nextLine[11]);
		gift_families_req = nextLine[12].isEmpty() ? 0 : Integer.parseInt(nextLine[12]);
		gift_families_assigned = nextLine[13].isEmpty() ? 0 : Integer.parseInt(nextLine[13]);
		orn_delivered = nextLine[14].isEmpty() ? 0 : Integer.parseInt(nextLine[14]);
		orn_rec_before = nextLine[15].isEmpty() ? 0 : Integer.parseInt(nextLine[15]);
		orn_rec_after = nextLine[16].isEmpty() ? 0 : Integer.parseInt(nextLine[16]);
		other = getDBString(nextLine[17]);
		deliverTo = getDBString(nextLine[18]);
		specialNotes = getDBString(nextLine[19]);
		contact = getDBString(nextLine[20]);
		contact_email = getDBString(nextLine[21]);
		contact_phone = getDBString(nextLine[22]);
		contact2 = getDBString(nextLine[23]);
		contact2_email = getDBString(nextLine[24]);
		contact2_phone = getDBString(nextLine[25]);
		pyRequested = nextLine[31].isEmpty() ? 0 : Integer.parseInt(nextLine[31]);
		pyAssigned = nextLine[32].isEmpty() ? 0 : Integer.parseInt(nextLine[32]);
		pyDelivered = nextLine[33].isEmpty() ? 0 : Integer.parseInt(nextLine[33]);
		pyReceivedBeforeDeadline = nextLine[34].isEmpty() ? 0 : Integer.parseInt(nextLine[34]);
		pyReceivedAfterDeadline = nextLine[35].isEmpty() ? 0 : Integer.parseInt(nextLine[35]);
	}
	
	String getDBString(String s)
	{
		return s.isEmpty() ? "" : s;
	}

	//getters
	public int getStatus()	{ return status; }
	PartnerType getType()	{ return type; }
	GiftCollection getGiftCollectionType() {return collection; }
	public String getName()	{ return name; }
//	String getOrnamentDelivery()	{ return ornamentDelivery; }
	public int getStreetnum()	{ return streetnum; }
	public String getStreetname()	{return streetname; }
	String getUnit() { return unit; }
	public String getCity()	{return city; }
	public String getZipcode()	{ return zipcode; }
	int getRegion()	{ return region; }
	String getPhone()	{ return phone; }
	public int getNumberOfOrnamentsRequested()	{ return gift_families_req; }
	public int getNumberOfOrnamentsAssigned() { return gift_families_assigned; }
	public int getNumberOfOrnamentsDelivered() { return orn_delivered; }
	public int getNumberOfOrnamentsReceivedBeforeDeadline() { return orn_rec_before; }
	public int getNumberOfOrnamentsReceivedAfterDeadline() { return orn_rec_after; }
	String getOther()	{ return other; }
	String getConfirmed() { return confirmed;}
	String getDeliverTo() { return deliverTo; }
	String getSpecialNotes()	{ return specialNotes; }
	String getContact()	{ return contact; }
	String getContact_email()	{ return contact_email; }
	String getContact_phone()	{ return contact_phone; }
	String getContact2()	{ return contact2; }
	String getContact2_email()	{ return contact2_email; }
	String getContact2_phone()	{ return contact2_phone; }
	int getPriorYearRequested() { return pyRequested; }
	int getPriorYearAssigned() { return pyAssigned; }
	int getPriorYearDelivered() { return pyDelivered; }
	int getPriorYearReceivedBeforeDeadline() { return pyReceivedBeforeDeadline; }
	int getPriorYearReceivedAfterDeadline() { return pyReceivedAfterDeadline; }
	
	//setters
	public void setStatus(int s)	{ status = s; }
	void setType(PartnerType t)		{ type = t; }
	void setGiftCollectionType(GiftCollection gc)	{ collection = gc; }
	void setName(String n)	{ name = n; }
//	void setOrnamentDelivery(String od)	{ ornamentDelivery = od; }
	void setStreetnum(int sn)	{ streetnum = sn; }
	void setStreetname(String sn)	{ streetname = sn; }
	void setUnit(String sn) { unit = sn; }
	void setCity(String c)	{ city = c; }
	void setZipcode(String z)	{ zipcode = z; }
	public void setRegion(int r)	{ region = r; }
	void setPhone(String p)	{ phone = p; }
	public void setNumberOfOrnamentsRequested(int n)	{ gift_families_req = n; }
	public void setNumberOfOrnamentsAssigned(int n)	{ gift_families_assigned = n; }
	public void setNumberOfOrnamentsDelivered(int n)	{ orn_delivered = n; }
	public void setNumberOfOrnamentsReceivedBeforeDeadline(int n)	{ orn_rec_before = n; }
	public void setNumberOfOrnamentsReceivedAfterDeadline(int n)	{ orn_rec_after = n; }
	void setOther(String o)	{ other = o; }
	void setConfirmed(String c) { confirmed = c;}
	void setDeliverTo(String dt) { deliverTo = dt; }
	void setSpecialNotes(String sn)	{ specialNotes = sn; }
	void setContact(String c)	{ contact = c; }
	void setContact_email(String e)	{ contact_email = e; }
	void setContact_phone(String p)	{ contact_phone = p; }
	void setContact2(String c)	{ contact2 = c; }
	void setContact2_email(String e)	{ contact2_email = e; }
	void setContact2_phone(String p)	{ contact2_phone = p; }
	public void setPriorYearRequested(int n) { pyRequested = n; }
	public void setPriorYearAssigned(int n) { pyAssigned = n; }
	public void setPriorYearDelivered(int n) { pyDelivered = n; }
	public void setPriorYearReceivedBeforeDeadline(int n) { pyReceivedBeforeDeadline = n; }
	public void setPriorYearReceivedAfterDeadline(int n) { pyReceivedAfterDeadline = n; }
	
	public int incrementOrnAssigned() { return ++gift_families_assigned; }
	public int decrementOrnAssigned()
	{
		if(gift_families_assigned > 0)
			gift_families_assigned--;
		return gift_families_assigned;
	}
	
	public int incrementOrnDelivered() { return ++orn_delivered; }
	public int decrementOrnDelivered()
	{
		if(orn_delivered > 0)
			orn_delivered--;
		return orn_delivered;
	}
	
	public int incrementOrnReceived(boolean bBeforeDeadline)
	{ 
		return  bBeforeDeadline ? ++orn_rec_before : ++orn_rec_after; 
	}
	
	public int decrementOrnReceived(boolean bBeforeDeadline)
	{
		if(bBeforeDeadline && orn_rec_before > 0)
			return --orn_rec_before;
		else if(!bBeforeDeadline && orn_rec_after > 0)
			return --orn_rec_after;
		else if(bBeforeDeadline && orn_rec_before <= 0)
			return orn_rec_before;
		else
			return orn_rec_after; 
	}
	
	public int incrementPYAssigned() { return ++pyAssigned; }
	public int incrementPYDelivered() { return ++pyDelivered; }
	public int incrementPYReceivedBeforeDeadline() { return ++pyReceivedBeforeDeadline; }
	public int incrementPYReceivedAfterDeadline() { return ++pyReceivedAfterDeadline; }
	
	String[] getOrgInfoTableRow()
	{
		String[] sorttablerow = {name, phone,
								contact, contact_email, contact_phone,
								contact2, contact2_email, contact2_phone};						 
		return sorttablerow;
	}
	
	public String[] getExportRow()
	{
		String[] row= {Integer.toString(id), Integer.toString(status),
						Integer.toString(type.type()),
						collection.toString(), name, 
//						ornamentDelivery, 
						Integer.toString(streetnum),
						streetname, unit, city, zipcode, Integer.toString(region), phone,
						Integer.toString(gift_families_req),Integer.toString(gift_families_assigned), Integer.toString(orn_delivered),
						Integer.toString(orn_rec_before), Integer.toString(orn_rec_after), 
						other, deliverTo, specialNotes, contact,
						contact_email, contact_phone, contact2, contact2_email, contact2_phone, 
						Long.toString(dateChanged.getTimeInMillis()), changedBy, Integer.toString(slPos),
						slMssg, slChangedBy, Integer.toString(pyRequested), Integer.toString(pyAssigned),
						Integer.toString(pyDelivered), Integer.toString(pyReceivedBeforeDeadline), 
						Integer.toString(pyReceivedAfterDeadline)};
		return row;
	}
	
	 @Override
	 public String toString()
	 {
	     return name;
	 }
}