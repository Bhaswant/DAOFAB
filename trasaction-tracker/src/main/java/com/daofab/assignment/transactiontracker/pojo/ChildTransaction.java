package com.daofab.assignment.transactiontracker.pojo;

import lombok.Data;

/**
 * 
 * Simulation of Child Transaction table. Child transactions are sorted by
 * default by id.
 * 
 * @author i0b00j8
 *
 */
@Data
public class ChildTransaction implements ITransaction, Comparable<ChildTransaction> {

	private final int id;

	private final int paidAmount;

	private final ParentTransaction parentTransaction;

	@Override
	public int compareTo(ChildTransaction o) {
		return this.id - o.id;
	}

	public ChildTransaction(final ParentTransaction parentTransaction, final int id, final int paidAmount) {
		this.parentTransaction = parentTransaction;
		this.id = id;
		this.paidAmount = paidAmount;
	}

	public ChildTransaction(final ChildTransaction transaction) {
		this.id = transaction.id;
		this.paidAmount = transaction.paidAmount;
		this.parentTransaction = new ParentTransaction(transaction.getParentTransaction());
	}
}
