package ed;

import ed.functional.core.Buffer;
import ed.parsing.CommandLine;

public class Ed
{
	private final String prompt;
	private Buffer currentBuffer;
	
	public Ed(String prompt, Buffer currentBuffer)
	{
		this.prompt = prompt;
		this.currentBuffer = currentBuffer;
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
