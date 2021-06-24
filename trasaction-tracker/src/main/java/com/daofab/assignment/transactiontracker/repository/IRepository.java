package com.daofab.assignment.transactiontracker.repository;

import com.daofab.assignment.transactiontracker.exception.TransactionTrackerException;

/**
 * Interface repository guiding the Parent and Child repositories. Generic to
 * support multiple types of save methods.
 * 
 * @author i0b00j8
 *
 */
public interface IRepository<T> {

	public void save(T obj) throws TransactionTrackerException;

}
