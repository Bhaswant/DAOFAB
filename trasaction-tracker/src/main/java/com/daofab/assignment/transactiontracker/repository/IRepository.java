package com.daofab.assignment.transactiontracker.repository;

import com.daofab.assignment.transactiontracker.exception.TransactionTrackerException;

/**
 * 
 * @author i0b00j8
 *
 */
public interface IRepository<T> {
	
	public void save(T obj) throws TransactionTrackerException;
	
}
