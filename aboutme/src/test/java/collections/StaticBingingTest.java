package collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticBingingTest {
	final private static Logger logger = LoggerFactory.getLogger( StaticBingingTest.class);

	public static void print ( Collection c ) {
		logger.info( "Printing Collection" );

	}

	public static void print ( HashSet h ) {
		logger.info( "Printing HashSet" );

	}

	public static void print ( Set s ) {
		logger.info( "Printing Set" );

	}


	@Test
	public void interview_tests ()
			throws Exception {
		logger.info( "Interview Testing" );
		
		Collection c = new HashSet();
		print(c);

	}
}

