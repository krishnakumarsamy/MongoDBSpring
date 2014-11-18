package org.kk.mongodbspring.common.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.kk.mongodbspring.common.Constants;
import org.kk.mongodbspring.common.MongoDBSpringException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * Connection class.
 * 
 * @author krishnakumar
 * 
 */
@Repository
public class DBConnection {

	private static final Logger logger = Logger.getLogger(DBConnection.class);

	private transient MongoClient client;
	private transient DB database;

	/**
	 * Read DB Connection properties from property file and establish
	 * connection.
	 * 
	 * @throws MongoDBSpringException
	 */
	@PostConstruct
	public void connection() throws MongoDBSpringException {
		final Properties properties = new Properties();
		logger.info("Loading momgodb.properties");
		ServerAddress serverAddress = null;
		try (InputStream input = new ClassPathResource(Constants.FILE_NAME_CONNECTION)
				.getInputStream();) {
			properties.load(input);
			serverAddress = new ServerAddress(properties.getProperty(Constants.TXT_DB_HOST),
					Integer.parseInt(properties.getProperty(Constants.TXT_DB_PORT)));

		} catch (IOException | NumberFormatException exception) {
			logger.error("Exception in loading " + Constants.FILE_NAME_CONNECTION, exception);
			throw new MongoDBSpringException(exception);
		}
		client = new MongoClient(serverAddress);
		database = client.getDB(properties.getProperty(Constants.TXT_DB_DBNAME));
	}

	/**
	 * @return DB object.
	 */
	public DB getDatabase() {
		return database;
	}

}
