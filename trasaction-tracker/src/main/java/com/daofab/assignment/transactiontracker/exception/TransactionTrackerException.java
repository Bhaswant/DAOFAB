package com.daofab.assignment.transactiontracker.exception;

/**
 * Custom exception to control the type of exceptions to be thrown and the
 * enforce messages
 * 
 * @author i0b00j8
 *
 */
public class TransactionTrackerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093830484667000981L;

	public TransactionTrackerException(final String msg) {
		super(msg);
	}
}
