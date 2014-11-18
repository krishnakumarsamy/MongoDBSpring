package org.kk.mongodbspring.converter;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.kk.mongodbspring.common.Constants;
import org.kk.mongodbspring.model.Student;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * Student model converter class for DAO layer and Service layer.
 * 
 * @author krishnakumar
 * 
 */
public class StudentConverter {
	private BasicDBObjectBuilder dbObjectBuilder;

	/**
	 * Default constructor.
	 */
	public StudentConverter() {
		dbObjectBuilder = new BasicDBObjectBuilder();
	}

	/**
	 * Convert list<student> objects to DBObject.
	 * 
	 * @param studentList
	 * @return
	 */
	public DBObject getDBObjectFromModel(final List<Student> studentList) {
		for (Student student : studentList) {
			addDocument(student);
		}
		return this.getDbObjectBuilder().get();
	}

	/**
	 * Convert student object to DBObject.
	 * 
	 * @param student
	 * @return DBObject for CRUD operations.
	 */
	public DBObject getDBObjectFromModel(final Student student) {
		return addDocument(student).get();
	}

	/**
	 * get the values from student and append into BasicDBObjectBuilder.
	 * 
	 * @param student
	 * @return
	 */
	private BasicDBObjectBuilder addDocument(final Student student) {
		this.getDbObjectBuilder()
				.add(Constants.TXT_OBJECTID,
						student.getId() != null ? new ObjectId(student.getId()) : null)
				.add("name", student.getName()).add("age", student.getAge())
				.add("sex", student.getSex()).add("address", student.getAddress());
		return this.getDbObjectBuilder();
	}

	/**
	 * Convert List of DBObject from database to List of Student object.
	 * 
	 * @param objects
	 * @return
	 */
	public List<Student> getModelFromDBObject(List<DBObject> objects) {
		List<Student> studentList = new ArrayList<Student>();
		for (DBObject object : objects) {
			studentList.add(getStudent(object));
		}
		return studentList;
	}

	/**
	 * Convert DBObject to student model object.
	 * 
	 * @param dbObject
	 * @return student model object.
	 */
	public Student getStudent(final DBObject dbObject) {
		Student student = new Student();
		student.setId(String.valueOf(dbObject.get(Constants.TXT_OBJECTID)));
		student.setName((String) dbObject.get("name"));
		student.setSex(String.valueOf(dbObject.get("sex")));
		student.setAge(Integer.parseInt(String.valueOf(dbObject.get("age"))));
		student.setAddress((String) dbObject.get("address"));
		return student;
	}

	/**
	 * @return BasicDBObjectBuilder
	 */
	public BasicDBObjectBuilder getDbObjectBuilder() {
		return dbObjectBuilder;
	}

	/**
	 * @param dbObjectBuilder
	 */
	public void setDbObjectBuilder(BasicDBObjectBuilder dbObjectBuilder) {
		this.dbObjectBuilder = dbObjectBuilder;
	}
}
