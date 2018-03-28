package ed.functional.core;

public class Question
{
	private final String message;
	
	public Question(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean isEqual = false;
		
		if (o instanceof Question)
		{
			Question q = (Question) o;
			isEqual = this.message == q.message;
		}
		
		return isEqual;
	}
}
