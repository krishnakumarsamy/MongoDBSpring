package org.kk.mongodbspring.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.kk.mongodbspring.common.Constants;
import org.kk.mongodbspring.dao.BaseDAO;
import org.kk.mongodbspring.dao.StudentDAO;
import org.kk.mongodbspring.exception.MongoDBSpringException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * DAO class for student controller.
 * 
 * @author krishnakumar
 * 
 */
@Repository
public class StudentDAOImpl implements StudentDAO {

	private static Logger logger = Logger.getLogger(StudentDAOImpl.class);

	@Autowired
	private transient BaseDAO baseDao;

	@Override
	public WriteResult addStudent(final DBObject object) throws MongoDBSpringException {
		final WriteResult result = baseDao.createDocument(Constants.COLL_STUDENT, object);
		logger.info("Saved Successfully.... - " + object.toString());
		return result;
	}

	@Override
	public List<DBObject> getAllStudent() throws MongoDBSpringException {
		final List<DBObject> studentList = baseDao.getDocuments(Constants.COLL_STUDENT);
		logger.info("getAllStudent:Number of Student Documents seleted - " + studentList.size());
		return studentList;
	}

	@Override
	public DBObject getStudent(final String studentId) throws MongoDBSpringException {
		final BasicDBObjectBuilder query = new BasicDBObjectBuilder();
		query.add(Constants.TXT_OBJECTID, new ObjectId(studentId));
		final List<DBObject> studentList = baseDao
				.getDocuments(Constants.COLL_STUDENT, query.get());
		logger.info("getStudent:Number of Documents seleted - " + studentList.size());
		return studentList.get(0);
	}

	@Override
	public WriteResult editStudent(final DBObject query, final DBObject student)
			throws MongoDBSpringException {
		logger.info("Edit Student Completed successfully");
		return baseDao.editDocument(Constants.COLL_STUDENT,
				getStudent(String.valueOf(student.get("_id"))), student);
	}

	@Override
	public WriteResult deleteStudent(final DBObject query) throws MongoDBSpringException {
		logger.info("Delete Student Completed successfully");
		return baseDao.deleteDocument(Constants.COLL_STUDENT, query);
	}

}
