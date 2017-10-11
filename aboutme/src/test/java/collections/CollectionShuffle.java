package collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionShuffle {
	final private static Logger logger = LoggerFactory.getLogger( CollectionShuffle.class );

	@Test
	public void CollectionShuffleTest ()
			throws Exception {


		int[] integers = {3,5,4,6,7,7,8,9,1};		
		List<Integer> integersToShuffle = Arrays
				.stream(integers)
				.boxed()
				.collect(Collectors.toList());
		
		logger.info( "Printing integersToShuffle {}", integersToShuffle );
		
		String[] words = { "cat", "hat", "mat", "fat", "sat", "pat" };
		List<String> wordstoShuffle = Arrays.asList( words );
		
		Collections.shuffle( wordstoShuffle );

		wordstoShuffle.stream()
			.forEach( e -> logger.info( "WordstoShuffle using stream : {}", e ) );

		List<String> shuffledList = wordstoShuffle.stream()
			.collect( Collectors.toList() );

		logger.info( "Printing shuffledList {}", shuffledList );

		for ( String arg : wordstoShuffle ) {
			logger.info( "WordsToShuffle: {}", arg );
		}

		System.out.println();
	}
}
