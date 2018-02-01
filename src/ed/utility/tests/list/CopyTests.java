package ed.utility.tests.list;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static ed.utility.ListUtility.*;

import org.junit.Test;

public class CopyTests
{
	@Test
	public void removingItems_FromCopiedList_DoesNotRemoveOriginal()
	{
		List<Integer> list = newList(1, 5);
		List<Integer> copy = copy(list);
		
		list.remove(0);
		list.remove(1);
		
		assertEquals("The size of copy must not have changed", 5, copy.size());
		assertTrue("The elements of copy must not have changed", copy.stream().allMatch(i -> i == 1));
	}
	
	@Test
	public void copyAdd_AddsItemsToCopiedList_DoesNotAlterOriginal()
	{
		List<Integer> list = newList(1, 5);
		List<Integer> copy = copyAdd(list, 2);
		
		assertEquals("The size of list must not have changed", 5, list.size());
		assertTrue("The original elements of list must not have changed", list.stream().allMatch(i -> i == 1));
		
		assertEquals("The size of copy must have changed", 6, copy.size());
		assertEquals("The last element of copy must have been added", new Integer(2), copy.get(copy.size() - 1));
		assertTrue("The original elements of copy must not have changed", copy.stream().limit(5).allMatch(i -> i == 1));
	}
	
	@Test
	public void copyAdd_ValidPosition_DoesNotAlterOriginal()
	{
		List<Integer> list = newList(1, 5);
		List<Integer> copy = copyAdd(list, 2, 3);
		
		assertEquals("The size of list must not have changed", 5, list.size());
		assertTrue("The original elements of list must not have changed", list.stream().allMatch(i -> i == 1));
		
		assertEquals("The size of copy must have changed", 6, copy.size());
		assertEquals("The fourth element of copy must have been added", Arrays.asList(1,1,1,2,1,1), copy);
	}
	
	@Test
	public void copyRemove_ValidItem_DoesNotAlterOriginal()
	{
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		List<Integer> copy = copyRemove(list, 4);
		
		assertEquals("The size of list must not have changed", 5, list.size());
		assertEquals("The original elements of list must not have changed", Arrays.asList(1,2,3,4,5), list);

		assertEquals("The size of copy must have changed", 4, copy.size());
		assertEquals("The fourth element of copy must have been removed", Arrays.asList(1,2,3,5), copy);
	}
	
	@Test
	public void copyRemove_InvalidItem_DoesNotAlterAnyList()
	{
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		List<Integer> copy = copyRemove(list, 0);
		
		assertEquals("The size of list must not have changed", 5, list.size());
		assertEquals("The original elements of list must not have changed", Arrays.asList(1,2,3,4,5), list);

		assertEquals("The copied list must be the same as the original", list, copy);
	}
}
