package ed.utility;

public class Pair<T1, T2>
{
	private final T1 f;
	private final T2 s;
	
	public Pair (T1 first, T2 second)
	{
		f = first;
		s = second;
	}
	
	public T1 first()
	{
		return f;
	}
	
	public T2 second()
	{
		return s;
	}
}
