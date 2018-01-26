package ed;

import ed.parsing.CommandLine;

public class Ed
{
	private State currentState;
	
	public Ed(State currentState)
	{
		this.currentState = currentState;
	}
	
	public static void main(String[] args)
	{
		String prompt = CommandLine.getPromptString(args);
		boolean suppress = CommandLine.suppress(args);
		
		
		for(String s : args)
		{
			System.out.println(s);
		}
		
		System.out.println();
		System.out.println(prompt);
		System.out.println(suppress);
		
		
		//new Ed(prompt);
	}
}
