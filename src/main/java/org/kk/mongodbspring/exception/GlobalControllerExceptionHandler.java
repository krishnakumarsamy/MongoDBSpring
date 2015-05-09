package org.kk.mongodbspring.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.kk.mongodbspring.common.Constants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Global Exception Handler for all the controllers.
 * 
 * @author krishnakumar
 * 
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = Logger.getLogger(GlobalControllerExceptionHandler.class);

	/**
	 * Handle all the exceptions that is not handled in all the controlers.
	 * 
	 * @param request
	 * @param response
	 * @param exception
	 * @throws IOException
	 */
	@ExceptionHandler(Exception.class)
	public void handleException(final HttpServletRequest request,
			final HttpServletResponse response, final Exception exception) throws IOException {
		logger.info("Common Exception ", exception);
		response.sendError(404);
	}

	/**
	 * Handle all the MongoDBSpringException
	 * 
	 * @param exception
	 * @param response
	 * @param modelAndView
	 * @return error page.
	 * @throws IOException
	 */
	@ExceptionHandler(MongoDBSpringException.class)
	public ModelAndView handleException(final MongoDBSpringException exception,
			final HttpServletResponse response) throws IOException {
		logger.info("Common Mongo Exception ", exception);
		return new ModelAndView(Constants.PAGE_ERROR, Constants.TXT_ERROR_MESSAGE,
				exception.getMessage());
	}

}
