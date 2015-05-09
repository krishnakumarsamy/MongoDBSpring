package org.mongodbspring.controller;

import org.kk.mongodbspring.controller.IndexController;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.Test;

public class IndexControllerTest {

	@InjectMocks
	private transient IndexController controller;

	private MockMvc mockMvc;

	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testIndex() {

	}

}
