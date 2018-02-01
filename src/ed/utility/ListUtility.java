package ed.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtility
{
	/**
	 * Ensures that number is between 0 and list.length, making it suitable for operating on the given list.
	 * This function does not behave sensibly if a negative list size is given; zero is returned.
	 * @param list The number of items in the list to test against. myList.size() is compatible with this call.
	 * @param number The number to test.
	 * @return 0, list.size() - 1, or number.
	 */
	public static int constrainNumber(int listSize, int number)
	{
		if (number <= 0 || listSize <= 0)
		{
			return 0;
		}
		
		if (number > listSize)
		{
			return listSize - 1;
		}
				
		return number;
	}
	
	public static <T> List<T> newList()
	{
		return new ArrayList<T>();
	}
	
	public static <T> List<T> newList(T fillerItem, int size)
	{
		List<T> newList = newList();
		
		for (int i = 0; i < size; i++)
		{
			newList.add(fillerItem);
		}
		
		return newList;
	}
	
	public static <T> List<T> fill(List<T> list, T placeholder, int from, int to)
	{
		List<T> newList = copy(list);
		
		for (int i = from; i < to; i++)
		{
			newList.add(i, placeholder);
		}
		
		return newList;
	}
	
	/**
	 * Creates a new list from the elements in list.
	 * Note that the elements themselves are not copied
	 * and may be modified by reference.
	 * 
	 * @param list The list to be copied.
	 */
	public static <T> List<T> copy(Collection<T> list)
	{
		return new ArrayList<T>(list);
	}
	
	/**
	 * copyAdd where item is added to the end of the list
	 * @param list The originating list. May not be null
	 * @param item The item to add to the new list. May be null.
	 * @return A new list containing all items in list, and item.
	 * list remains unmodified.
	 */
	public static <T> List<T> copyAdd(List<T> list, T item)
	{
		return copyAdd(list, item, list.size());
	}
	
	/**
	 * Add item to position in list.
	 * @param list The originating list.
	 * @param item The item to add.
	 * @param position The position to add it to. Constrained according to constrainNumber.
	 * @return A new list with item at position.
	 */
	public static <T> List<T> copyAdd(List<T> list, T item, int position)
	{
		List<T> copy = copy(list);
		copy.add(constrainNumber(copy.size(), position), item);
		return copy;
	}
	
	/**
	 * Creates a new list from list, keeping only items that
	 * match toKeep. Predicates can be found under ed.utility.Predicates.
	 * @param list The originating list. May not be null.
	 * @param toKeep The predicate to test each item against. May not be null.
	 * @return A new list consisting only of items in list that match toKeep.
	 * The items themselves are not copied.
	 */
	public static <T> List<T> filterList(List<T> list, Predicate<T> toKeep)
	{
		return list.stream()
				.filter(toKeep)
				.collect(Collectors.toList());
	}

	public static <T> List<T> copyRemove(List<T> list, T item)
	{
		List<T> copy = copy(list);
		copy.remove(item);
		return copy;
	}
}
