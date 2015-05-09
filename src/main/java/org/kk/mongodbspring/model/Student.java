package org.kk.mongodbspring.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * Model class for student.
 * 
 * @author krishnakumar
 * 
 */
public class Student implements Serializable {

	private static final long serialVersionUID = -8367640103011497582L;

	private String id;
	@NotNull
	@Length(min = 3, max = 100, message = "Name should contains minimum length of 3 and maximum length of 100 characters.")
	private String name;
	@Range(min = 10, max = 150, message = "Age should between 10 to 150")
	private int age;
	private String sex;
	@NotBlank(message = "Address should not be blank")
	private String address;
	private String operation;

	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            - Student name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            - Student age.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            - Student addres
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            - Student ID.
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            - Add/Delete/Edit.
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		StringBuffer values = new StringBuffer();
		values.append("\tName=").append(this.getName()).append("\tAge=").append(this.getAge())
				.append("\tSex=").append(this.getSex()).append("\tAddress=")
				.append(this.getAddress());
		return values.toString();
	}

	public Student(final String name, final String address, final int age, final String sex) {
		this.setAddress(address);
		this.setSex(sex);
		this.setName(name);
		this.setAge(age);
	}

	public Student() {
	}

}
