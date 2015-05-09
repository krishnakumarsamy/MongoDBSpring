package org.kk.mongodbspring.controller;

import static org.kk.mongodbspring.common.Constants.TXT_COMMAND;

import org.apache.log4j.Logger;
import org.kk.mongodbspring.common.Constants;
import org.kk.mongodbspring.exception.MongoDBSpringException;
import org.kk.mongodbspring.model.Student;
import org.kk.mongodbspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author krishnakumar
 * 
 */
@Controller(value = "/")
public class IndexController {

	private static final Logger logger = Logger.getLogger(IndexController.class);

	@Autowired
	private transient StudentService studentService;

	/**
	 * Load index page.
	 * 
	 * @param model
	 * @return
	 * @throws MongoDBSpringException
	 */
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String index(ModelMap model) throws MongoDBSpringException {
		logger.info("inside index method");
		model.addAttribute(TXT_COMMAND, new Student());
		model.addAttribute(Constants.TXT_STUDENTS, studentService.getAllStudents());
		return Constants.PAGE_INDEX;
	}

}
