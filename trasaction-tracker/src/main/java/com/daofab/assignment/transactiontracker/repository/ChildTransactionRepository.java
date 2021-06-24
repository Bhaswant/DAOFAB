package com.daofab.assignment.transactiontracker.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.daofab.assignment.transactiontracker.exception.TransactionTrackerException;
import com.daofab.assignment.transactiontracker.pojo.ChildTransaction;
import com.daofab.assignment.transactiontracker.pojo.VirtualDatabase;

/**
 * Repository class working on ChildTransaction Pojo. All ChildTransaction
 * related queries are dealt from this class
 * @author i0b00j8
 *
 */
@Component
public class ChildTransactionRepository implements IRepository<ChildTransaction> {

	@Override
	public void save(final ChildTransaction childTransaction) throws TransactionTrackerException {
		
		if(!VirtualDatabase.PARENT_TRANSACTIONS.containsKey(childTransaction.getParentTransaction().getId())) {
			throw new TransactionTrackerException("Parent transaction not found");
		}

		VirtualDatabase.CHILD_TRANSACTIONS.put(childTransaction.getId(), childTransaction);
		if(!VirtualDatabase.PARENT_CHILD_MAPPING.containsKey(childTransaction.getParentTransaction().getId())) {
			VirtualDatabase.PARENT_CHILD_MAPPING.put(childTransaction.getParentTransaction().getId(), new TreeSet<>());
		}
		
		VirtualDatabase.PARENT_CHILD_MAPPING.get(childTransaction.getParentTransaction().getId()).add(childTransaction.getId());
	}
	
	public List<ChildTransaction> getChildTransactions(final int parentId) {
		SortedSet<Integer> childIds = VirtualDatabase.PARENT_CHILD_MAPPING.get(parentId);
		if(childIds == null) {
			return Collections.emptyList();
		}
		List<ChildTransaction> output = new ArrayList<>();
		
		for(int childId : childIds) {
			output.add(VirtualDatabase.CHILD_TRANSACTIONS.get(childId));
		}
		
		return output;
	}
	
	public int getChildTransactionsSize(final int parentId) {
		return VirtualDatabase.PARENT_CHILD_MAPPING.get(parentId).size();
	}


}
