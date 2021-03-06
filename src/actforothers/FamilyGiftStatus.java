package actforothers;

public enum FamilyGiftStatus 
{
	Any (0, "Any"),
	No_Change (0, "No Change"),
	NotRequested (0, "Not Requested"),
	Requested(1, "Requested"),
	Selected (2,"Selected"),
	Received (3,"Received"),
	Verified (4, "Verified"),
	Exported (5, "Exported"),
	Assigned(6,"Assigned"),
	Referred (7, "Referred"),
	Attempted (8, "Attempted"),
	Returned (9, "Returned"),
	CounselorPickUp (10, "Counselor Pick-Up");
	
	private final int statusIndex;
	private final String english;
	
	FamilyGiftStatus(int statusIndex, String english)
	{
		this.statusIndex = statusIndex;
		this.english = english;
	}
	
	public String toString() { return this.english; }
	
	static FamilyGiftStatus getFamilyGiftStatus(int statusIndex)
	{
		FamilyGiftStatus result = FamilyGiftStatus.NotRequested;
		for(FamilyGiftStatus fgs : FamilyGiftStatus.getSearchList())
			if(fgs.statusIndex == statusIndex)
			{
				result = fgs;
				break;
			}
		
			return result;
	}
	public int statusIndex() { return statusIndex; }
	String english() { return english; }
	
	static FamilyGiftStatus[] getSearchList()
	{
		FamilyGiftStatus[] fgsSearch = {FamilyGiftStatus.NotRequested,
										FamilyGiftStatus.Requested, 
										FamilyGiftStatus.Assigned, 
										FamilyGiftStatus.Referred};
		
		return fgsSearch;
	}
	
	static FamilyGiftStatus[] getSearchFilterList()
	{
		FamilyGiftStatus[] fgsSearch = {FamilyGiftStatus.Any, FamilyGiftStatus.NotRequested,
										FamilyGiftStatus.Requested,
										FamilyGiftStatus.Assigned, 
										FamilyGiftStatus.Referred};
		
		return fgsSearch;
	}
	
	static FamilyGiftStatus[] getChangeList()
	{
		FamilyGiftStatus[] fgsChange = {FamilyGiftStatus.No_Change, FamilyGiftStatus.NotRequested, 
										FamilyGiftStatus.Requested,
										FamilyGiftStatus.Assigned, 
										FamilyGiftStatus.Referred};
		
		return fgsChange;
	}	
}