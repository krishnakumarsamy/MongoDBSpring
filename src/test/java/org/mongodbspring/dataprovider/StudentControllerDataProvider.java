package org.mongodbspring.dataprovider;

import org.kk.mongodbspring.model.Student;
import org.testng.annotations.DataProvider;

public class StudentControllerDataProvider {

	@DataProvider(name = "FormValidationErrors")
	public static Object[][] fieldsToCheckFormErrors() {
		return new Object[][] {
				{ new Student("", "#34 XYZ Street", 27, "M"), new String[] { "name" } },
				{ new Student("", "#34 XYZ Street", 0, "M"), new String[] { "age", "name" } },
				{ new Student("", "", 0, "M"), new String[] { "age", "address", "name" } },
				{ new Student("", "", 8, "M"), new String[] { "age" } },
				{ new Student("", "", 1000, "M"), new String[] { "age", } },
				{ new Student("", "", -120, "M"), new String[] { "age", } },
				{ new Student("", "Kr", 25, "M"), new String[] { "name" } }, };

	}

	@DataProvider(name = "addStudent")
	public static Object[][] addStudent() {
		return new Object[][] { { new Student("Krish", "#34 XYZ Street", 27, "M") },
				{ new Student("Krishnakumar", "#35 XYZ Street", 28, "M") } };

	}

	@DataProvider(name = "editStudent")
	public static Object[][] editStudent() {
		return new Object[][] { { "001" }, { "002" } };
	}

	@DataProvider(name = "deleteStudent")
	public static Object[][] deleteStudent() {
		return new Object[][] { { "001" }, { "002" } };
	}
}
