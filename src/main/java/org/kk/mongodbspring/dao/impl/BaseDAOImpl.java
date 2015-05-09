package org.kk.mongodbspring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.kk.mongodbspring.common.connection.DBConnection;
import org.kk.mongodbspring.dao.BaseDAO;
import org.kk.mongodbspring.exception.MongoDBSpringException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

/**
 * DAO class for common methods.
 * 
 * @author krishnakumar
 * 
 */
@Repository
public class BaseDAOImpl implements BaseDAO {

	private static Logger logger = Logger.getLogger(BaseDAOImpl.class);

	@Autowired
	private transient DBConnection connection;

	/**
	 * Used to get the collection from DB.
	 * 
	 * @param collectionName
	 * @return collection
	 */
	private DBCollection getDocument(final String collectionName) {
		logger.info("Inside getDocument");
		return connection.getDatabase().getCollection(collectionName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mongodbspring.dao.BaseDAO#getDocuments(java.lang.String,
	 * com.mongodb.DBObject)
	 */
	public List<DBObject> getDocuments(final String collectionName, final DBObject query)
			throws MongoDBSpringException {
		logger.info("Inside getDocuments");
		return getListOfDocuments(getDocument(collectionName).find(query));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mongodbspring.dao.BaseDAO#getDocuments(java.lang.String)
	 */
	public List<DBObject> getDocuments(final String collectionName) throws MongoDBSpringException {
		logger.info("Inside getDocuments");
		return getListOfDocuments(getDocument(collectionName).find());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mongodbspring.dao.BaseDAO#createDocument(java.lang.String,
	 * com.mongodb.DBObject)
	 */
	public WriteResult createDocument(final String collectionName, final DBObject object)
			throws MongoDBSpringException {
		logger.info("Inside createDocument start");
		WriteResult result = null;
		try {
			result = getDocument(collectionName).insert(object);
			logger.info("New document created");
		} catch (MongoException exception) {
			logger.error("Exception in Inside createDocument", exception);
			throw new MongoDBSpringException(exception);
		}
		logger.info("Inside createDocument end");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mongodbspring.dao.BaseDAO#editDocument(java.lang.String,
	 * com.mongodb.DBObject, com.mongodb.DBObject)
	 */
	public WriteResult editDocument(String collectionName, DBObject query, DBObject object)
			throws MongoDBSpringException {
		logger.info("Inside editDocument start");
		WriteResult result = null;
		try {
			result = getDocument(collectionName).update(query, object);
			logger.info("Update completed");
		} catch (MongoException exception) {
			logger.error("Exception in editDocument", exception);
			throw new MongoDBSpringException(exception);
		}
		logger.info("Inside editDocument end");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mongodbspring.dao.BaseDAO#deleteDocument(java.lang.String,
	 * com.mongodb.DBObject)
	 */
	public WriteResult deleteDocument(String collectionName, DBObject query)
			throws MongoDBSpringException {
		logger.info("Inside deleteDocument start");
		WriteResult result = null;
		try {
			result = getDocument(collectionName).remove(query);
			logger.info("deleteDocument completed");
		} catch (MongoException exception) {
			logger.error("Exception in deleteDocument", exception);
			throw new MongoDBSpringException(exception);
		}
		logger.info("Inside deleteDocument end");
		return result;
	}

	/**
	 * Used to iterate DBCursor and retrive each document.
	 * 
	 * @param cursor
	 *            - It contains all the documments(DBObjects).
	 * @return List<DBObject>
	 * @throws MongoDBSpringException
	 */
	private List<DBObject> getListOfDocuments(final DBCursor cursor) throws MongoDBSpringException {
		final List<DBObject> documentList = new ArrayList<DBObject>();
		try {
			while (cursor.hasNext()) {
				documentList.add(cursor.next());
			}
		} catch (MongoException exception) {
			logger.error("Exception in getListOfDocuments", exception);
			throw new MongoDBSpringException(exception);
		}
		return documentList;
	}

}
