package ed.utility;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import ed.functional.core.Buffer;

public class TestUtility
{
	/**
	 * Replace the first occurrence of toReplace with replacement in line.
	 * No parameters may be null.
	 * @param lines The lines that make up the buffer. May be empty.
	 * @param toReplace The string or regex to replace in the string.
	 * @param replacement The string to replace toReplace.
	 * @return A list identical to lines with all instances of toReplace replaced with replacement.
	 * @throws PatternSyntaxException Iff toReplace is an invalid regex.
	 */
	public static List<String> replaceFirst(List<String> lines, String toReplace, String replacement)
	{

		return Arrays.asList(
				String.join("", lines) // Create a regular string from the array
				.replaceFirst(toReplace, replacement) // Replace the first item as specified
				.split("") // Turn String into String[]
				);
	}

	public static Buffer createBuffer(List<String> lines, int address)
	{
		return new Buffer(lines, Buffer.getDefaultPrompt(), address);
	}

	public static Buffer createBuffer()
	{
		return new Buffer();
	}
}
