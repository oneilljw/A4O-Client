package actforothers;

public enum CollectionType
{
	Any ("Any"), 
	Unknown ("Unknown"),
	Gifts ("Gifts"),
	Meals ("Meals"),
	GiftsMeals ("Gifts & Meals");
	
	private String name;
	
	CollectionType(String name)
	{
		this.name = name;
	}
	
	static CollectionType[] filterValues()
	{
		CollectionType[] gcSelectionValues = {CollectionType.Any, CollectionType.Unknown, CollectionType.Gifts,
											  CollectionType.Meals, CollectionType.GiftsMeals};
		
		return gcSelectionValues;
	}
	
	static CollectionType[] selectionValues()
	{
		CollectionType[] gcSelectionValues = {CollectionType.Unknown, CollectionType.Gifts,
				  								CollectionType.Meals, CollectionType.GiftsMeals};
		
		return gcSelectionValues;
	}
	
	@Override
	public String toString() { return name; }
}

