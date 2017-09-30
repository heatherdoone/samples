package com.example.aboutme;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith ( SpringRunner.class )
@SpringBootTest ( classes = AboutmeApplication.class )
@ActiveProfiles ( "junit" )
public class AboutmeApplicationTests {

	final static private Logger logger = LoggerFactory.getLogger( AboutmeApplicationTests.class );

	@BeforeClass
	public static void setUpBeforeClass ()
			throws Exception {
		// InitializeLogging.printTestHeader( logger.getName() );

		logger.info( "Getting started" );

	}

	// @Autowired
	@Inject
	private ApplicationContext applicationContext;

	@Test
	public void contextLoads () {

		assertThat(
			applicationContext.getBeanDefinitionCount() )
				.as( "Spring Bean count" )
				.isGreaterThan( 15 );

		List<String> beanList = Arrays.asList( applicationContext.getBeanDefinitionNames() );

		StringBuilder beanInfo = new StringBuilder( "Spring Beans" );

		
		beanList.stream()
			.sorted()
			.forEach( bean -> {
				beanInfo.append(  "\n\t" + bean );
			} );
		
		
//		AtomicInteger beanCount = new AtomicInteger();
		
//		beanList
//			.stream()
//			.sorted()
//			.forEach( bean -> {
//
//				String desc = bean;
//				if ( desc.length() > 20 ) {
//					desc = desc.substring(
//						desc.length() - 20,
//						desc.length() );
//				}
//				beanInfo.append( "\t" + bean + "\n" );
//
////				if ( beanCount.incrementAndGet() % 2 == 0 ) {
////					beanInfo.append( "\n\t" );
////				}
//				;
//			} );

		logger.info( "\n\n Bean graph for heather: \n\n{}\n\n\n", beanInfo.toString() );
	}

}
