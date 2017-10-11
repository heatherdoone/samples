package collections;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateNumberSequence {
	final private static Logger logger = LoggerFactory.getLogger( GenerateNumberSequence.class );

	@Test
	public void GenerateFibonacciNumberSequence ()
			throws Exception {

		List<Integer> fibonacciNumbers = new ArrayList<Integer>();
		fibonacciNumbers.add( 0 );
		fibonacciNumbers.add( 1 );

		for ( int term = 2; term < 30; term++ ) {
			int term_minus_2 = fibonacciNumbers.get( term - 2 );
			int term_minus_1 = fibonacciNumbers.get( term - 1 );
			fibonacciNumbers.add( term_minus_2 + term_minus_1 );
		}

		logger.info( "Printing 30 fibonacciNumbers {}", fibonacciNumbers );
		// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987,
		// 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393,
		// 196418, 317811,..
	}

	@Test
	public void GenerateOddNumberSequence ()
			throws Exception {

		List<Integer> oddNumbers = new ArrayList<Integer>();
		
		for ( int term = 0; term < 20; term++ ) {
			oddNumbers.add( 2 * term + 1 );
		}

		logger.info( "Printing 20 oddNumbers {}", oddNumbers );

	}
	
	
	@Test
	public void GenerateEvenNumberSequence ()
			throws Exception {

		List<Integer> evenNumbers = new ArrayList<Integer>();
		
		for ( int term = 0; term < 20; term++ ) {
			evenNumbers.add( 2 * term );
		}

		logger.info( "Printing 20 evenNumbers {}", evenNumbers );

	}
}
