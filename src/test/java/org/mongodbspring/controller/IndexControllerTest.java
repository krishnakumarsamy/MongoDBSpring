package org.mongodbspring.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.kk.mongodbspring.common.Constants;
import org.kk.mongodbspring.controller.IndexController;
import org.kk.mongodbspring.model.Student;
import org.kk.mongodbspring.service.StudentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration("file:src/main/webapp/WEB-INF/MongoDBSpring-servlet.xml")
public class IndexControllerTest extends AbstractTestNGSpringContextTests {

	@InjectMocks
	private transient IndexController controller;

	@Mock
	private transient StudentService studentService;

	@Mock
	private transient List<Student> studentList;

	private MockMvc mockMvc;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testIndexPage() throws Exception {
		when(studentService.getAllStudents()).thenReturn(studentList);
		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(view().name(Constants.PAGE_INDEX))
				.andExpect(model().attributeExists(Constants.TXT_STUDENTS))
				.andExpect(model().attributeExists(Constants.TXT_COMMAND)).andReturn();
		// We can do the same as in TestNG as like below.
		// MvcResult result =
		// mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();
		// assertEquals(Constants.PAGE_INDEX,
		// result.getModelAndView().getViewName());
		// assertEquals(Constants.PAGE_INDEX,
		// result.getModelAndView().getViewName());
	}
}
