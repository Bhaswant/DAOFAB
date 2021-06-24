package com.daofab.assignment.transactiontracker.pojo;

import lombok.Data;

/**
 * Simulation of Parent Transaction table.
 * Transactions support sorting through various comparators.
 * 
 * @author i0b00j8
 *
 */
@Data
public class ParentTransaction implements ITransaction {

	private final int id;

	private final String sender;

	private final String receiver;

	private final int totalAmount;

	public ParentTransaction(final int id, final String sender, final String receiver, final int totalAmount) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.totalAmount = totalAmount;
	}

	/**
	 * For the purpose of deep copy
	 * 
	 * @param parentTransaction
	 */
	public ParentTransaction(final ParentTransaction parentTransaction) {
		this.id = parentTransaction.id;
		this.sender = parentTransaction.sender;
		this.receiver = parentTransaction.receiver;
		this.totalAmount = parentTransaction.totalAmount;
	}
}
