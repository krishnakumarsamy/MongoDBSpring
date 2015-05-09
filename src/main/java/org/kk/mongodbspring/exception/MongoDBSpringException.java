package org.kk.mongodbspring.exception;

/**
 * Custom Exception class.
 * 
 * @author krishnakumar
 * 
 */
public class MongoDBSpringException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3935177185157433937L;

	/**
	 * Default constructor.
	 */
	public MongoDBSpringException() {
		super();
	}

	/**
	 * @param thtowable
	 */
	public MongoDBSpringException(final Throwable thtowable) {
		super(thtowable);
	}

	/**
	 * @param message
	 */
	public MongoDBSpringException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public MongoDBSpringException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

}
