package ed.functional.core.tests.buffer;

import static ed.utility.TestUtility.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ed.functional.core.Buffer;

public class AddToCurrentAddressTests
{
	@Test
	public void stringToEmptyBuffer_doesNotChangeEmptyBuffer()
	{
		Buffer empty = createBuffer();
		int emptyAddress = empty.getAddress();
		List<String> emptyLines = empty.getLines();

		empty.addToCurrentAddress("test");

		assertEquals("Buffer address must not change on adding new line", emptyAddress, empty.getAddress());
		assertEquals("Buffer lines must not change on adding new line", emptyLines, empty.getLines());
	}

	@Test
	public void stringToEmptyBufferZero_newBufferHasOnlyGivenLine()
	{
		Buffer nonEmpty = createBuffer().addToCurrentAddress("test");

		List<String> expectedLines = Arrays.asList("test");
		Buffer expected = createBuffer(expectedLines, 0);

		assertEquals("New buffer must contain only the added line", expected, nonEmpty);
	}

	@Test
	public void stringToEmptyBuffer_bufferHasNewString()
	{
		Buffer nonEmpty = createBuffer().addToCurrentAddress("content");

		List<String> expectedLines = Arrays.asList("content");
		Buffer expected = createBuffer(expectedLines, 0); // Address remains 0.

		assertEquals("New buffer must contain only the added line", expected, nonEmpty);
	}
	
	@Test
	public void stringToFullBuffer_bufferHasNewString()
	{
		Buffer nonEmpty = createBuffer()
				.addToCurrentAddress("first")
				.addToCurrentAddress("second")
				.addToCurrentAddress("third");

		// The address doesn't change, so we displace the previous placement
		// leading to backwards ordering.
		List<String> expectedLines = Arrays.asList("third", "second", "first");
		Buffer expected = createBuffer(expectedLines, 0);

		assertEquals("New buffer must contain all previous lines and the added line with the address unchanged", expected, nonEmpty);
	}
	
	@Test
	public void negativeAddressOutsideValidRange_bufferHasNewStringAt0()
	{
		Buffer negativeBuffer = createBuffer()
					.addToEnd("extra")
					.setAddress(-1)
					.addToCurrentAddress("content");
		
		assertEquals("New buffer must have address equal to 0", 0, negativeBuffer.getAddress());
		
		List<String> actual = negativeBuffer.getLines();
		List<String> expected = Arrays.asList("content", "extra");
		
		assertEquals("The content should be added to the front if a negative address is given", expected, actual);
	}

	@Test
	public void positiveAddressOutsideValidRange_bufferHasNewStringAtSize()
	{
		Buffer buffer = createBuffer()
				.addToEnd("extra")
				.addToEnd("more")
				.setAddress(3)
				.addToCurrentAddress("content");
		
		System.out.println(buffer.getLines());
		System.out.println(buffer.getAddress());
		
		assertEquals("New buffer must have address equal to size", 2, buffer.getAddress());
		
		List<String> actual = buffer.getLines();
		List<String> expected = Arrays.asList("extra", "more", "content");
		
		assertEquals("The content should be added to the end if an oversize address is given", expected, actual);
	}
}
