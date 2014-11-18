package org.kk.mongodbspring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.kk.mongodbspring.common.Constants;
import org.kk.mongodbspring.common.MongoDBSpringException;
import org.kk.mongodbspring.common.Operations;
import org.kk.mongodbspring.common.URI;
import org.kk.mongodbspring.model.Student;
import org.kk.mongodbspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author krishnakumar
 * 
 */
@Controller
public class StudentController {

	@Autowired
	private transient StudentService studentService;

	/**
	 * Hanadles add person operation.
	 * 
	 * @param student
	 * @param result
	 * @param modelMap
	 * @return view
	 * @throws MongoDBSpringException
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { URI.ADDPERSON })
	public String addStudent(@ModelAttribute(Constants.TXT_COMMAND) @Valid Student student,
			final BindingResult result, final ModelMap modelMap) throws MongoDBSpringException {
		String viewName = Constants.PAGE_FORWARD_INDEX;
		if (result.hasErrors()) {
			return Constants.PAGE_INDEX;
		}
		final String status = studentService.addStudent(student);
		modelMap.addAttribute(Constants.TXT_STATUS, status);
		modelMap.addAttribute(Constants.TXT_STUDENTS, studentService.getAllStudents());
		return viewName;
	}

	/**
	 * Edit person details.
	 * 
	 * @param request
	 * @param modelMap
	 * @return view
	 * @throws MongoDBSpringException
	 */
	@RequestMapping(value = URI.EDITPERSON, method = { RequestMethod.POST })
	public String editStudent(final HttpServletRequest request, final ModelMap modelMap)
			throws MongoDBSpringException {
		modelMap.addAttribute(Constants.TXT_COMMAND,
				getStudentById(Operations.EDIT, request.getParameter(Constants.TXT_ID)));
		return Constants.PAGE_INDEX;
	}

	/**
	 * Delete person.
	 * 
	 * @param request
	 * @param modelMap
	 * @return view
	 * @throws MongoDBSpringException
	 */
	@RequestMapping(value = URI.DELETEPERSON, method = { RequestMethod.POST })
	public String deleteStudent(final HttpServletRequest request, final ModelMap modelMap)
			throws MongoDBSpringException {
		modelMap.addAttribute(Constants.TXT_COMMAND,
				getStudentById(Operations.DELETE, request.getParameter(Constants.TXT_ID)));
		return Constants.PAGE_INDEX;

	}

	/**
	 * Add / Edit / Delete Studetn based on Id.
	 * 
	 * @param operation
	 * @param studentId
	 * @return
	 * @throws MongoDBSpringException
	 */
	private Student getStudentById(Operations operation, final String studentId)
			throws MongoDBSpringException {
		final Student student = studentService.getStudent(studentId);
		student.setOperation(operation.name());
		return student;
	}

}
