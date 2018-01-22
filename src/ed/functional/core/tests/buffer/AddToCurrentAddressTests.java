package ed.functional.core.tests.buffer;

import static ed.functional.core.tests.buffer.Utility.*;
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
	public void stringToNonZeroAddress_bufferHasNewStringAtAddress()
	{
		Buffer nonEmpty = createBuffer()
				.setAddress(1)
				.addToCurrentAddress("first");

		// The address doesn't change, so we displace the previous placement
		// leading to backwards ordering.
		List<String> expectedLines = Arrays.asList("first");
		Buffer expected = createBuffer(expectedLines, 1);

		assertEquals("New buffer must only the added line and have an address of 1", expected, nonEmpty);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void negativeAddressOutsideValidRange_throwsIndexOutOfBounds()
	{
		createBuffer()
		.setAddress(-1)
		.addToCurrentAddress("content");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void positiveAddressOutsideValidRange_throwsIndexOutOfBounds()
	{
		createBuffer()
		.setAddress(2)
		.addToCurrentAddress("content");
	}
}
