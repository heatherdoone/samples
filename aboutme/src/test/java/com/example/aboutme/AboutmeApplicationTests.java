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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.aboutme.albums.Album;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith ( SpringRunner.class )
@SpringBootTest ( classes = AboutmeApplication.class )
@ConfigurationProperties ( prefix = "heather.samples" )
@ActiveProfiles ( "junit" )
public class AboutmeApplicationTests {
	final static private Logger logger = LoggerFactory.getLogger( AboutmeApplicationTests.class );


	public void setTest1 ( String test1 ) {
		this.test1 = test1;
	}

	public void setTest2 ( int test2 ) {
		this.test2 = test2;
	}

	private String test1 = null;
	private int test2 = -1;


	@BeforeClass
	public static void setUpBeforeClass ()
			throws Exception {
		// InitializeLogging.printTestHeader( logger.getName() );

		logger.info( "Getting started" );

	}

	// @Autowired
	@Inject
	private ApplicationContext applicationContext;
	@Inject
	private Environment springEnv;

	@Test
	public void validate_spring_context_loaded () {

		assertThat(
			applicationContext.getBeanDefinitionCount() )
				.as( "Spring Bean count" )
				.isGreaterThan( 15 );

		List<String> beanList = Arrays.asList( applicationContext.getBeanDefinitionNames() );

		StringBuilder beanInfo = new StringBuilder( "Spring Beans" );

		beanList.stream()
			.sorted()
			.forEach( bean -> {
				beanInfo.append( "\n\t" + bean );
			} );

		logger.info( "\n\n Bean graph for heather: \n\n{}\n\n\n", beanInfo.toString() );
	}

	@Test
	public void validate_spring_environment_available () {
		logger.info( "heather.test    {}",
			springEnv.getProperty( "heather.test" ) );

		assertThat(
			springEnv.getProperty( "heather.test" ) )
				.as( "HD Test" )
				.isEqualTo( "16" );
	}

	@Test
	public void validate_spring_configuration_properties_available () {
		logger.info( " test1:   {}, test2: {}", test1, test2 );

		assertThat(
			springEnv.getProperty( "heather.test" ) )
				.as( "HD Test" )
				.isEqualTo( "16" );
	}

	@Inject
	ObjectMapper jsonMapper;

	static class SimplePerson {
		@Override
		public String toString () {
			return "TestJson [title=" + name + ", age=" + age + "]";
		}

		public String name;
		public String age;
	}

	@Test
	public void test_simple_json ()
			throws Exception {

		ObjectNode testJsonObject = jsonMapper.createObjectNode();

		testJsonObject.put( "title", "tay" );
		testJsonObject.put( "age", 5 );

		SimplePerson simplePerson = jsonMapper.readValue(
			testJsonObject.toString(),
			SimplePerson.class );

		logger.info( "testJsonObject: {}\n simplePerson: {}",
			testJsonObject,
			simplePerson );

	}

	@Test
	public void test_album ()
			throws Exception {

		ObjectNode albumObject = jsonMapper.createObjectNode();

		albumObject.put( "title", "taylor" );
		albumObject.put( "description", "Age 5" );

		Album tayAlbum = jsonMapper.readValue(
			albumObject.toString(),
			Album.class );

		logger.info( "albumObject: {}\n Album: {}",
			albumObject,
			tayAlbum );

		assertThat(
			tayAlbum.getTitle() )
				.as( "Album Title" )
				.isEqualTo( "taylor" );
	}

	@Test
	public void verify_album_with_extra_field_is_ignored ()
			throws Exception {

		ObjectNode albumObject = jsonMapper.createObjectNode();

		albumObject.put( "title", "invalidTitle" );
		albumObject.put( "description", "Age 5" );
		albumObject.put( "invalid", "Age 6" );

		Album tayAlbum = jsonMapper.readValue(
			albumObject.toString(),
			Album.class );

		logger.info( "albumObject: {}\n Album: {}",
			albumObject,
			tayAlbum );

		assertThat(
			tayAlbum.getTitle() )
				.as( "Album Title" )
				.isEqualTo( "invalidTitle" );
	}

	
	@Test
	public void verify_album_with_missing_field_is_ignored ()
			throws Exception {

		ObjectNode albumObject = jsonMapper.createObjectNode();

		albumObject.put( "title", "invalidTitle" );


		Album tayAlbum = jsonMapper.readValue(
			albumObject.toString(),
			Album.class );

		logger.info( "albumObject: {}\n Album: {}",
			albumObject,
			tayAlbum );

		assertThat(
			tayAlbum.getTitle() )
				.as( "Album Title" )
				.isEqualTo( "invalidTitle" );
	}

}
