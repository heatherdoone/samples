package collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClass {

	final private static Logger logger = LoggerFactory.getLogger( BaseClass.class );

	protected String message = new String( "This is the Protected message" );

	private Integer phoneNumber = new Integer( 222445555 );

	public void displayResult () {
		message.toUpperCase();

		int minimumValue = phoneNumber.MIN_VALUE;
		int maximumValue = phoneNumber.MAX_VALUE;

		logger.info( "Printing protected message from BaseClass: {} : {} : {}",
			message, minimumValue, maximumValue );

	}

}
