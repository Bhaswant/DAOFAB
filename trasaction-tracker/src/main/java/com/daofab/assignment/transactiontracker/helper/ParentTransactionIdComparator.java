package com.daofab.assignment.transactiontracker.helper;

import org.springframework.stereotype.Component;

import com.daofab.assignment.transactiontracker.pojo.ParentTransaction;

/**
 * Id comparator to allow the capability of parent sorting. This could also be
 * done using lambda expressions, but using comparator for better readability
 * and extensibility
 * 
 * @author i0b00j8
 *
 */
@Component
public class ParentTransactionIdComparator implements IParentComparator {

	@Override
	public int compare(ParentTransaction o1, ParentTransaction o2) {
		return o1.getId() - o2.getId();
	}

}
