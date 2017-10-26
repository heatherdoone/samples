package collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubClass extends BaseClass {
	final private static Logger logger = LoggerFactory.getLogger( SubClass.class );

	public void displayResult () {

		logger.info( "Displaying message from SubClass:  {}", message );

		// super.displayResult();

	}

	public static void main ( String args[] ) {

		SubClass obj = new SubClass();

		obj.displayResult();

		BaseClass baseObject = new BaseClass();

		baseObject.displayResult();

	}

}