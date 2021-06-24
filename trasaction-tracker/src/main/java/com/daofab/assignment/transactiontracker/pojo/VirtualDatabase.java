package com.daofab.assignment.transactiontracker.pojo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;

/**
 * This is a simulation of DB. Ideally, we need three tables here: 
 * 1. Parent Transactions 
 * 2. Child Transactions 
 * 3. Parent Child mapping. 
 * Also, all the operations like sorting, limiting the results will be delegated to DB
 * queries. Here, we will do them in-memory.
 */
public class VirtualDatabase {

	/**
	 * Indicates parent table. Here, if it is a relational DB, we will have a
	 * separate table with parent id as a foreign key. We will create a field in the
	 * ParentTransaction for Child transaction.
	 */
	public static final Map<Integer, ParentTransaction> PARENT_TRANSACTIONS = new LinkedHashMap<>();

	public static final Map<Integer, ChildTransaction> CHILD_TRANSACTIONS = new LinkedHashMap<>();
	
	public static final Map<Integer, SortedSet<Integer>> PARENT_CHILD_MAPPING = new LinkedHashMap<>();
	
	/**
	 * Private constructor to avoid creating objects to this class.
	 */
	private VirtualDatabase() {
		
	}
}
