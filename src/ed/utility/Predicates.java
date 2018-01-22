package ed.utility;

public class Predicates
{
	public static <T> boolean isNull(T item)
	{
		return item == null;
	}
	
	public static <T> boolean isNonNull(T item)
	{
		return !isNull(item);
	}
}
