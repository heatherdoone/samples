package collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayListDuplicateTest {
	final private static Logger logger = LoggerFactory.getLogger( ArrayListDuplicateTest.class );

	@Test
	public void ArrayListDuplicate_Test ()
			throws Exception {
		logger.info( "ArrayListDuplicateTest Start" );

		// creating ArrayList with duplicate elements
		List<Integer> listWithDuplicates = new ArrayList<Integer>();

		listWithDuplicates.add( 2 );
		listWithDuplicates.add( 3 );
		listWithDuplicates.add( 5 );
		listWithDuplicates.add( 7 ); // duplicate
		listWithDuplicates.add( 7 );
		listWithDuplicates.add( 11 );

		logger.info( "list of numbers with Duplicates : {} ", listWithDuplicates );

		// Now let's remove duplicate element without affecting order
		// LinkedHashSet will guaranteed the order and since it's a set
		// it will not allow us to insert duplicates.
		// repeated elements will automatically filtered.

		Set<Integer> listWithoutDuplicates = new LinkedHashSet<Integer>( listWithDuplicates );

		// now let's clear the ArrayList so that we can copy all elements from
		// LinkedHashSet
		listWithDuplicates.clear();

		// copying elements but without any duplicates
		listWithDuplicates.addAll( listWithoutDuplicates );

		logger.info( "list of numbers without duplicates : {} ", listWithDuplicates );

	}

	@Test
	public void ArrayListDuplicate_usingStream_Test ()
			throws Exception {
		logger.info( "ArrayListDuplicateTest with Streams Start" );

		// creating ArrayList with duplicate elements
		List<Integer> listWithDuplicates = new ArrayList<Integer>();

		listWithDuplicates.add( 2 );
		listWithDuplicates.add( 3 );
		listWithDuplicates.add( 5 );
		listWithDuplicates.add( 7 ); // duplicate
		listWithDuplicates.add( 7 );
		listWithDuplicates.add( 11 );

		logger.info( "list of numbers : {} ", listWithDuplicates );

		Object[] listwithoutDuplicates = listWithDuplicates
			.stream()
			.collect( Collectors.toSet() )
			.toArray();

		logger.info( "list of numbers without duplicates : {} ", listwithoutDuplicates );

		Set<Integer> setWithoutDuplicates = listWithDuplicates
			.stream()
			.collect( Collectors.toSet() );

		logger.info( "Set of numbers without duplicates : {} ", setWithoutDuplicates );

		listWithDuplicates.clear();

		listWithDuplicates.addAll( setWithoutDuplicates );

		logger.info( "Original list of numbers without duplicates : {} ", listWithDuplicates );

	}

	@Test
	public void FindDuplicateIntegerList_Test ()
			throws Exception {
		logger.info( "FindDuplicateIntegerList_Test with Streams Start" );

		// creating ArrayList with duplicate elements
		List<Integer> listWithDuplicates = new ArrayList<Integer>();

		listWithDuplicates.add( 2 );
		listWithDuplicates.add( 3 );
		listWithDuplicates.add( 5 );
		listWithDuplicates.add( 7 );
		listWithDuplicates.add( 3 );
		listWithDuplicates.add( 7 );
		listWithDuplicates.add( 11 );
		listWithDuplicates.add( 3 );

		logger.info( "list of numbers : {} ", listWithDuplicates );

		Set<Integer> setWithoutDuplicates = listWithDuplicates
			.stream()
			.collect( Collectors.toSet() );

		logger.info( "Set of numbers without duplicates : {} ", setWithoutDuplicates );

		List<Integer> listWithNoDuplicates = new ArrayList<Integer>();
		listWithNoDuplicates.addAll( setWithoutDuplicates );

		logger.info( "Printing listWithNoDuplicates : {} ", setWithoutDuplicates );

	}

	@Test
	public void FindDuplicateIntegerMap_Test ()
			throws Exception {
		logger.info( "FindDuplicateIntegerList_Test with Streams Start" );

		// creating ArrayList with duplicate elements
		List<Integer> listWithDuplicates = new ArrayList<Integer>();

		listWithDuplicates.add( 7 );
		listWithDuplicates.add( 5 );
		listWithDuplicates.add( 3 );
		listWithDuplicates.add( 3 );
		listWithDuplicates.add( 7 );
		listWithDuplicates.add( 11 );
		listWithDuplicates.add( 3 );
		listWithDuplicates.add( 5 );
		listWithDuplicates.add( 3 );
		listWithDuplicates.add( 2 );

		logger.info( "list of numbers : {} ", listWithDuplicates );

		Map<Integer, Integer> mapIndexToValue = new LinkedHashMap<Integer, Integer>();

		for ( ListIterator<Integer> iterator = listWithDuplicates.listIterator(); iterator.hasNext(); ) {
			mapIndexToValue.put( iterator.nextIndex(), iterator.next() );
		}

		logger.info( "Map of duplicate list : {} ", mapIndexToValue );

		Map<Integer, Integer> mapValueFrequency = new LinkedHashMap<Integer, Integer>();

		for ( Integer value : listWithDuplicates ) {
			Integer frequency = mapValueFrequency.get( value );
			mapValueFrequency.put( value, (frequency == null) ? 1 : frequency + 1 );
		}

		logger.info( "Map of numbers with frequency : {} ", mapValueFrequency );

		List<Integer> duplicatesOnly = new ArrayList<Integer>();
		for ( Map.Entry<Integer, Integer> entryItem : mapValueFrequency.entrySet() ) {
			if ( entryItem.getValue() > 1 ) {
				duplicatesOnly.add( entryItem.getKey() );
			}
		}
		logger.info( "printing contents of duplicates only {}", duplicatesOnly );

		// This solves the problem. Above are other ways to look at the data

		Map<Integer, List<Integer>> mapMultipleIndices = new LinkedHashMap<Integer, List<Integer>>();

		for ( ListIterator<Integer> iterator = listWithDuplicates.listIterator(); iterator.hasNext(); ) {

			Integer nextInteger = iterator.next();

			List<Integer> indexList = mapMultipleIndices.get( nextInteger );

			if ( indexList == null ) {
				mapMultipleIndices.put( nextInteger, indexList = new ArrayList<Integer>() );
			}

			indexList.add( iterator.previousIndex() );
		}

		logger.info( "Map of duplicatelist : {} ", mapMultipleIndices );

		Map<Integer, Integer> least2ndIndexMap = new HashMap<Integer, Integer>();

		for ( Map.Entry<Integer, List<Integer>> entryItem : mapMultipleIndices.entrySet() ) {
			List<Integer> indexList = entryItem.getValue();
			Integer index = 1;
			if ( indexList.size() > 1 && indexList.get( index ) != null ) {
				least2ndIndexMap.put( indexList.get( index ), entryItem.getKey() );
			}
		}

		logger.info( "printing Value to least 2nd index Map{}", least2ndIndexMap );

		List<Integer> keySetList = new ArrayList<Integer>();
		keySetList.addAll( least2ndIndexMap.keySet() );

		Integer least2ndIndex = keySetList.get( 0 );

		logger.info( "Printing keySetList : {}  least2ndIndex : {} ", keySetList, least2ndIndex );

		Integer least2ndIndexValue = -1;

		for ( Map.Entry<Integer, Integer> entryItem : least2ndIndexMap.entrySet() ) {

			if ( entryItem.getKey().equals( least2ndIndex ) ) {
				least2ndIndexValue = entryItem.getValue();
				logger.info( "printing index Value {}", least2ndIndex );
			}
		}

		logger.info( "printing final least 2nd index Value {}", least2ndIndexValue );

	}

	@Test
	public void firstDuplicateWithLeast2ndOccuranceIndexTest ()
			throws Exception {
		logger.info( "firstDuplicate Test Start" );
		Integer returnValue;
		
		int[] duplicateIntegers = { 3, 4, 5, 6, 7, 9, 0, 3, 0, 5, 7, 5, 4 };
		returnValue = firstDuplicateWithLeast2ndOccuranceIndex( duplicateIntegers );
		logger.info( "printing duplicateIntegers return Value {}", returnValue );
		
		assertThat( returnValue ).isEqualTo( 3 );
		
		int[] nointegers = {};
		returnValue = firstDuplicateWithLeast2ndOccuranceIndex( nointegers );
		logger.info( "printing nointegers return Value {}", returnValue );
		
		assertThat( returnValue ).isEqualTo( -1 );

		
		int[] noDuplicateintegers = {0,1,2,3,4,5,6};
		returnValue = firstDuplicateWithLeast2ndOccuranceIndex( noDuplicateintegers );
		logger.info( "printing noDuplicateintegers return Value {}", returnValue );
		
		assertThat( returnValue ).isEqualTo( -1 );

		
		int[] customDupTest13 = {28, 19, 13, 6, 34, 48, 50, 3, 47, 18, 15, 34, 16, 11, 13, 3, 2, 4, 46, 6,
		                         7, 3, 18, 9, 32, 21, 3, 21, 50, 10, 45, 13, 22, 1, 27, 18, 3, 27, 30, 44, 
		                         12, 30, 40, 1, 1, 31, 6, 18, 33, 5};
		
		returnValue = firstDuplicateWithLeast2ndOccuranceIndex( customDupTest13 );
		logger.info( "printing customDupTest13 return Value {}", returnValue );
		
		assertThat( returnValue ).isEqualTo( 34 );

	}

	int firstDuplicateWithLeast2ndOccuranceIndex ( int[] a ) {
		
		String [] letters = {"a", "b", "c"};
		List<String> listWithDuplicateLetters = Arrays.asList(letters);
		logger.info( "printing listWithDuplicateLetters : {} ", listWithDuplicateLetters );
		
		List<Integer> listWithDuplicates = Arrays
			.stream( a )
			.boxed()
			.collect( Collectors.toList() );

		logger.info( "printing listWithDuplicates : {} ", listWithDuplicates );

		Map<Integer, List<Integer>> mapValuesToMultipleIndices = new LinkedHashMap<Integer, List<Integer>>();

		for ( ListIterator<Integer> iterator = listWithDuplicates.listIterator(); iterator.hasNext(); ) {

			Integer nextInteger = iterator.next();

			List<Integer> indexList = mapValuesToMultipleIndices.get( nextInteger );

			if ( indexList == null ) {
				mapValuesToMultipleIndices.put( nextInteger, indexList = new ArrayList<Integer>() );
			}

			indexList.add( iterator.previousIndex() );
		}

		logger.info( "Map of duplicatelist : {} ", mapValuesToMultipleIndices );

		Map<Integer, Integer> least2ndIndexMap = new TreeMap<Integer, Integer>();

		for ( Map.Entry<Integer, List<Integer>> entryItem : mapValuesToMultipleIndices.entrySet() ) {
			List<Integer> indexList = entryItem.getValue();

			Integer secondOccuranceIndex = 1;

			if ( indexList.size() > 1 && indexList.get( secondOccuranceIndex ) != null ) {

				least2ndIndexMap.put( indexList.get( secondOccuranceIndex ), entryItem.getKey() );

			}

		}

		logger.info( "printing Value to least 2nd index Map{}", least2ndIndexMap );

		List<Integer> keySetList = new LinkedList<Integer>();
		keySetList.addAll( least2ndIndexMap.keySet() );
		logger.info( "Printing keySetList : {}  ", keySetList );

		Integer least2ndIndexValue = -1;
		int loopOnly1Time = 0;

		for ( Map.Entry<Integer, Integer> entryItem : least2ndIndexMap.entrySet() ) {
			if ( loopOnly1Time < 1 ) {
				least2ndIndexValue = entryItem.getValue();

				logger.info( "printing index Value {}", least2ndIndexValue );
			}

			loopOnly1Time++;

		}

		logger.info( "printing final least 2nd index Value {}", least2ndIndexValue );

		return least2ndIndexValue;

	}

}
