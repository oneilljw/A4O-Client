package actforothers;

public enum PartnerType 
{
	Any (0, "Any"),
	Unknown (0, "Unknown"),
	Association(8, "Association"),
	Business (1, "Business"),
	Charity(9, "Charity"),
	Church(2, "Church"),
	Individual (7, "Individual"),
	School(3, "School"),
	Clothing(4, "Clothing"),
	Coat (5, "Coat"),
	ONCShopper (6, "ONC Shopper");
	
	private int type;
	private String name;
		
	PartnerType(int type, String name)
	{
		this.type = type;
		this.name = name;
	}
	
	static PartnerType getPartnerType(int type)
	{
		PartnerType partnerType = PartnerType.Unknown;
		
		for(PartnerType pt : PartnerType.values())
		{
			if(pt.type() == type)
			{
				partnerType = pt;
				break;
			}	
		}
		
		return partnerType;
	}
	
	int type() { return type; }
	
	@Override
	public String toString() { return name; }
	
	static PartnerType[] getFilterTypes()
	{
		return new PartnerType[] { PartnerType.Any, PartnerType.Association, PartnerType.Business, 
									PartnerType.Charity, PartnerType.Church, PartnerType.Individual,
									PartnerType.School, PartnerType.Unknown };
	}
}
