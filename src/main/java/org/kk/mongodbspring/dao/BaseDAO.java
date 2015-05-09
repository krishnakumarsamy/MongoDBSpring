package org.kk.mongodbspring.dao;

import java.util.List;

import org.kk.mongodbspring.exception.MongoDBSpringException;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * @author krishnakumar
 * 
 */
public interface BaseDAO {

	/**
	 * Used to get all the documents for the passed collection.
	 * 
	 * 
	 * @param collectionName
	 *            - Name of the collection
	 * @return DBObject - all the documents for the given collection name.
	 * @throws MongoDBSpringException
	 */
	public List<DBObject> getDocuments(final String collectionName) throws MongoDBSpringException;

	/**
	 * Used to get all the documents for a particular collection with query.
	 * 
	 * @param collectionName
	 *            - Name of the collection
	 * @param query
	 *            - This query param is used to filter the collection given.
	 * @return - all the documents based on query param.
	 * @throws MongoDBSpringException
	 */
	public List<DBObject> getDocuments(final String collectionName, final DBObject query)
			throws MongoDBSpringException;

	/**
	 * Create a new document.
	 * 
	 * @param collectionName
	 *            - Name of the collection
	 * @param object
	 *            - object to be inserted into the given collection.
	 * @return - result of insertion.
	 * 
	 * @throws MongoDBSpringException
	 */

	public WriteResult createDocument(final String collectionName, final DBObject object)
			throws MongoDBSpringException;

	/**
	 * Update an existing documents.
	 * 
	 * @param collectionName
	 *            - Name of the collection
	 * @param query
	 *            - update will happen based on this query param.
	 * @param object
	 *            - object to be updated for the above query results.
	 * @throws MongoDBSpringException
	 * @return- result of updation.
	 */
	public WriteResult editDocument(final String collectionName, final DBObject query,
			final DBObject object) throws MongoDBSpringException;

	/**
	 * Delete documents based on query.
	 * 
	 * @param collectionName
	 *            - Name of the collection
	 * @param query
	 *            - documents to be deleted based on this query param.
	 * @return - deleted status.
	 * @throws MongoDBSpringException
	 */
	public WriteResult deleteDocument(final String collectionName, final DBObject query)
			throws MongoDBSpringException;
}
