package org.kk.mongodbspring.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.kk.mongodbspring.common.Operations;
import org.kk.mongodbspring.converter.StudentConverter;
import org.kk.mongodbspring.dao.StudentDAO;
import org.kk.mongodbspring.exception.MongoDBSpringException;
import org.kk.mongodbspring.model.Student;
import org.kk.mongodbspring.service.helper.StudentServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for Student.
 * 
 * @author krishnakumar
 * 
 */
@Service
public class StudentService {

	private final Logger logger = Logger.getLogger(StudentService.class);

	@Autowired
	private transient StudentDAO studentDao;

	/**
	 * Service method for Add student details.
	 * 
	 * @param student
	 * @return
	 * @throws MongoDBSpringException
	 */
	public String addStudent(final Student student) throws MongoDBSpringException {
		String status;
		if (Operations.EDIT.name().equalsIgnoreCase(student.getOperation())) {
			logger.info("Edit Student details");
			status = editStudent(student);
		} else if (Operations.DELETE.name().equalsIgnoreCase(student.getOperation())) {
			logger.info("Delete Student details");
			status = deleteStudent(student);
		} else {
			logger.info("Add Student details");
			status = StudentServiceHelper.getResult(studentDao.addStudent(new StudentConverter()
					.getDBObjectFromModel(student)));
		}
		return status;
	}

	/**
	 * Service method for get all the student details.
	 * 
	 * @return
	 * @throws MongoDBSpringException
	 */
	public List<Student> getAllStudents() throws MongoDBSpringException {
		return new StudentConverter().getModelFromDBObject(studentDao.getAllStudent());
	}

	/**
	 * Service method for get student based on student id.
	 * 
	 * @param studentId
	 * @return
	 * @throws MongoDBSpringException
	 */
	public Student getStudent(final String studentId) throws MongoDBSpringException {
		return new StudentConverter().getStudent(studentDao.getStudent(studentId));
	}

	/**
	 * Service method for edit student details.
	 * 
	 * @param student
	 * @return
	 * @throws MongoDBSpringException
	 */
	private String editStudent(final Student student) throws MongoDBSpringException {
		return StudentServiceHelper.getResult(studentDao.editStudent(
				studentDao.getStudent(student.getId()),
				new StudentConverter().getDBObjectFromModel(student)));
	}

	/**
	 * Service method for delete student details.
	 * 
	 * @param student
	 * @return
	 * @throws MongoDBSpringException
	 */
	private String deleteStudent(final Student student) throws MongoDBSpringException {
		return StudentServiceHelper.getResult(studentDao.deleteStudent(studentDao
				.getStudent(student.getId())));
	}
}
