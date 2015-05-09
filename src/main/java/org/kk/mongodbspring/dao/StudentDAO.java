package org.kk.mongodbspring.dao;

import java.util.List;

import org.kk.mongodbspring.exception.MongoDBSpringException;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * @author krishnakumar
 * 
 */
public interface StudentDAO {

	/**
	 * Add student details into DB.
	 * 
	 * @param object
	 * @return
	 * @throws MongoDBSpringException
	 */
	public WriteResult addStudent(DBObject object) throws MongoDBSpringException;

	/**
	 * Retrieve all the student details.
	 * 
	 * @return
	 * @throws MongoDBSpringException
	 */
	public List<DBObject> getAllStudent() throws MongoDBSpringException;

	/**
	 * Retrieve student details.
	 * 
	 * @param studentId
	 *            - used for querying DB.
	 * @return - DBObject
	 * @throws MongoDBSpringException
	 */
	public DBObject getStudent(final String studentId) throws MongoDBSpringException;

	/**
	 * Edit student details.
	 * 
	 * @param query
	 *            - Update the details based on the query param.
	 * @param object
	 *            - Updated person details.
	 * @return
	 * @throws MongoDBSpringException
	 */
	public WriteResult editStudent(final DBObject query, final DBObject object)
			throws MongoDBSpringException;

	/**
	 * Delete student details.
	 * 
	 * @param query
	 *            - Based on query param values, search and delete the person
	 *            details.
	 * @return
	 * @throws MongoDBSpringException 
	 */
	public WriteResult deleteStudent(final DBObject query) throws MongoDBSpringException;
}
