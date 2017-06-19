package actforothers;

public enum MealStatus 
{
	Any, No_Change, None, Requested, Assigned, Referred;

	static MealStatus[] getSearchFilterList()
	{
		MealStatus[] msSearch = {MealStatus.Any,
									MealStatus.None, 
									MealStatus.Requested,
									MealStatus.Assigned, 
									MealStatus.Referred};
									

		return msSearch;
	}
	
	static MealStatus[] getChangeList()
	{
		MealStatus[] msSearch = {MealStatus.No_Change, 
									MealStatus.Referred};
		return msSearch;
	}
}
