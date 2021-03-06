package actforothers;

public enum FamilyStatus 
{
	Any (0, "Any"),
	No_Change (0, "No Change"),
	Unverified (0, "Unverified"),
	Verified(1, "Verified"),
	Contacted (2,"Contacted"),
	Confirmed (3, "Confirmed"),
	Referred (4, "Referred");

	private final int statusIndex;
	private final String english;
	
	FamilyStatus(int statusIndex, String english)
	{
		this.statusIndex = statusIndex;
		this.english = english;
	}
	
	public String toString() { return this.english; }
	
	public static FamilyStatus getFamilyStatus(int statusIndex)
	{
		FamilyStatus result = FamilyStatus.Unverified;
		for(FamilyStatus fs : FamilyStatus.getSearchList())
			if(fs.statusIndex == statusIndex)
			{
				result = fs;
				break;
			}
		
			return result;
	}
	public int statusIndex() { return statusIndex; }
	String english() { return english; }
	
	static FamilyStatus[] getSearchList()
	{
		FamilyStatus[] fgsSearch = {FamilyStatus.Unverified, FamilyStatus.Verified,
									FamilyStatus.Referred};
		
		return fgsSearch;
	}
	
	static FamilyStatus[] getSearchFilterList()
	{
		FamilyStatus[] fgsSearch = {FamilyStatus.Any, FamilyStatus.Unverified, FamilyStatus.Verified,
									FamilyStatus.Referred};
		
		return fgsSearch;
	}
	
	static FamilyStatus[] getChangeList()
	{
		FamilyStatus[] fgsSearch = {FamilyStatus.No_Change, FamilyStatus.Unverified, FamilyStatus.Verified,
									FamilyStatus.Referred};
		
		return fgsSearch;
	}
}
