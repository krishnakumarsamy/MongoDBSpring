package org.mongodbspring.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.kk.mongodbspring.common.Constants;
import org.kk.mongodbspring.common.URI;
import org.kk.mongodbspring.controller.StudentController;
import org.kk.mongodbspring.exception.MongoDBSpringException;
import org.kk.mongodbspring.model.Student;
import org.kk.mongodbspring.service.StudentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mongodbspring.dataprovider.StudentControllerDataProvider;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author krishnakumar
 * 
 */
@ContextConfiguration("file:src/main/webapp/WEB-INF/MongoDBSpring-servlet.xml")
public class StudentControllerTest extends AbstractTestNGSpringContextTests {

	@InjectMocks
	private transient StudentController studentController;

	private transient MockMvc mockMvc;

	@Mock
	private transient Student student;

	@Mock
	private transient StudentService studentService;

	@Mock
	private transient List<Student> studentList;

	@BeforeClass
	public void setUp() throws MongoDBSpringException {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		when(studentService.addStudent(any(Student.class)))
				.thenReturn(Constants.TXT_STATUS_SUCCESS);
		when(studentService.getAllStudents()).thenReturn(studentList);
		when(studentService.getStudent(any(String.class))).thenReturn(student);
	}

	@Test(dataProvider = "FormValidationErrors", dataProviderClass = StudentControllerDataProvider.class)
	public void testAddStudent1FormValidationErrors(final Student student, final String[] fields)
			throws Exception {
		System.out.println(student.toString());
		mockMvc.perform(
				post("/" + URI.ADDPERSON).requestAttr(Constants.TXT_COMMAND, student)
						.param("name", student.getName())
						.param("age", String.valueOf(student.getAge()))
						.param("sex", student.getSex()).param("address", student.getAddress()))
				.andExpect(status().isOk()).andExpect(model().hasErrors())
				.andExpect(model().attributeHasFieldErrors("command", fields))
				.andExpect(view().name(Constants.PAGE_INDEX));

	}

	@Test(dataProvider = "addStudent", dataProviderClass = StudentControllerDataProvider.class)
	public void testAddStudentSuccess(final Student student) throws Exception {
		mockMvc.perform(
				post("/" + URI.ADDPERSON).requestAttr(Constants.TXT_COMMAND, student)
						.param("name", student.getName())
						.param("age", String.valueOf(student.getAge()))
						.param("sex", student.getSex()).param("address", student.getAddress()))
				.andExpect(status().isOk()).andExpect(model().hasNoErrors())
				.andExpect(view().name(Constants.PAGE_FORWARD_INDEX));

	}

	@Test(dataProvider = "editStudent", dataProviderClass = StudentControllerDataProvider.class)
	public void testEditStudentSuccess(final String studentId) throws Exception {
		mockMvc.perform(post("/" + URI.EDITPERSON).requestAttr(Constants.TXT_ID, studentId))
				.andExpect(status().isOk()).andExpect(model().hasNoErrors())
				.andExpect(model().attributeExists(Constants.TXT_COMMAND))
				.andExpect(view().name(Constants.PAGE_INDEX));

	}

	@Test(dataProvider = "deleteStudent", dataProviderClass = StudentControllerDataProvider.class)
	public void testDeleteStudentSuccess(final String studentId) throws Exception {
		mockMvc.perform(post("/" + URI.DELETEPERSON).requestAttr(Constants.TXT_ID, studentId))
				.andExpect(status().isOk()).andExpect(model().hasNoErrors())
				.andExpect(model().attributeExists(Constants.TXT_COMMAND))
				.andExpect(view().name(Constants.PAGE_INDEX));

	}
}
