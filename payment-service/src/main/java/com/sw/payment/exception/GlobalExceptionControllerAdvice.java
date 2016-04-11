package com.sw.payment.exception;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/**
 The exceptions below could be raised by any
 * controller and they would be handled here, if not handled in the controller
 * already.
 * 
 */

/**
 * For all exceptions other than custom exception implement an @ExceptionHandler method on a @ControllerAdvice
 * @author NIS1598-mbpr
 *
 */
@ControllerAdvice
public class GlobalExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	protected Logger logger;
	public static final String DEFAULT_ERROR_VIEW = "error";

	public GlobalExceptionControllerAdvice() {
		logger = LoggerFactory.getLogger(getClass());
	}

	
	/**
	 * Convert a predefined exception to an HTTP Status code
	 */
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Kamal Data integrity violation")
	// 409
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void conflict() {
		logger.error("Request raised a DataIntegrityViolationException");
		// Nothing to do
	}

	/**
	 * Convert a predefined exception to an HTTP Status code and specify the
	 * name of a specific view that will be used to display the error.
	 * 
	 * @return Exception view.
	 */
	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public String databaseError(Exception exception) {
		// Nothing to do. Return value 'databaseError' used as logical view name
		// of an error page, passed to view-resolver(s) in usual way.
		logger.error("Request raised " + exception.getClass().getSimpleName());
		return "databaseError";
	}

	/**
	 * Demonstrates how to take total control - setup a model, add useful
	 * information and return the "support" view name. This method explicitly
	 * creates and returns
	 * 
	 * @param req
	 *            Current HTTP request.
	 * @param exception
	 *            The exception thrown - always {@link SupportInfoException}.
	 * @return The model and view used by the DispatcherServlet to generate
	 *         output.
	 * @throws Exception
	 */
	@ExceptionHandler(SupportInfoException.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception)
			throws Exception {

		// Rethrow annotated exceptions or they will be processed here instead.
		if (AnnotationUtils.findAnnotation(exception.getClass(),ResponseStatus.class) != null)
			throw exception;

		logger.error("Request: " + req.getRequestURI() + " raised " + exception);

		// Otherwise setup and send the user to a default error-view.
		
		ModelAndView mav = new ModelAndView("/support");
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("timestamp", new Date().toString());
		mav.addObject("status", 500);
		return mav;
	}
	
	/**@ExceptionHandler(SupportInfoException.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex)
			throws Exception {
		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errMsg", "this is Exception.class");
		return model;
	}**/
	
	
	/**
	 * Convert a predefined exception to an HTTP Status code
	 */
	//@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Kamal Data integrity violation")
	// 409
	//@ExceptionHandler(DataValidationException.class)
	/*public ResponseEntity<DataValidationException>  inValidData(HttpServletRequest req, Exception e) {
		logger.error("Be good, always send valid request !!");
		DataValidationException error = new DataValidationException(e.toString() + "Be good, always send valid request !!" , req.getRequestURI());
		return new ResponseEntity<DataValidationException>(error, HttpStatus.NOT_FOUND);
		
		// Nothing to do
	}*/
	
	 @ExceptionHandler(value = { DataValidationException.class})
	    public  ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
	        String bodyOfResponse = ex.getMessage() + "Be good, always send valid request !!";
	        return handleExceptionInternal(ex, bodyOfResponse, 
	          new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
	    }
	 
	 @ExceptionHandler(value = { FirstDataException.class})
	    public  ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
	        String bodyOfResponse = ex.getMessage() + "Why Why Why Mr anderson. Why Blame me !";
	        return handleExceptionInternal(ex, bodyOfResponse, 
	          new HttpHeaders(), HttpStatus.BAD_GATEWAY, request);
	    }

}

