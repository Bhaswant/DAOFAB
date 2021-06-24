package com.daofab.assignment.transactiontracker.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daofab.assignment.transactiontracker.helper.IParentComparator;
import com.daofab.assignment.transactiontracker.pojo.ParentTransaction;
import com.daofab.assignment.transactiontracker.pojo.VirtualDatabase;

/**
 * This is a simulation of DB. Ideally, we need three tables here: 1. Parent
 * Transactions 2. Child Transactions 3. Parent Child mapping. Also, all the
 * operations like sorting, limiting the results will be delegated to DB
 * queries. Here, we will do them in-memory
 * 
 * @author i0b00j8
 *
 */
@Component
public class ParentTransactionRepository implements IRepository<ParentTransaction> {

	@Autowired
	private IParentComparator parentTransactionIdComparator;

	private Map<String, IParentComparator> comparatorMap;

	/**
	 * Scope to add transactions dynamically
	 */
	@Override
	public void save(final ParentTransaction parentTransaction) {
		VirtualDatabase.PARENT_TRANSACTIONS.put(parentTransaction.getId(), parentTransaction);
	}

	/**
	 * Gets parent transactions from the repository. Ideally, offset and sorting
	 * should be sent as a SQL query. Since we are using in-memory DB, this logic is
	 * in repository.
	 * 
	 * @param offset
	 * @param size
	 * @param sortCol
	 * @return
	 */
	public List<ParentTransaction> getParentTransaction(final int offset, final int size, final String sortCol) {
		
		if(comparatorMap == null) {
			comparatorMap = new HashMap<>();
			comparatorMap.put("parentId", parentTransactionIdComparator);

		}
		// It is service's responsibility to provide valid offset
		if (offset >= VirtualDatabase.PARENT_TRANSACTIONS.size()) {
			return Collections.emptyList();
		}
		List<ParentTransaction> parentTransactions;
		if (sortCol != null && comparatorMap.containsKey(sortCol)) {
			parentTransactions = deepCopy(VirtualDatabase.PARENT_TRANSACTIONS);
			Collections.sort(parentTransactions, comparatorMap.get(sortCol));
		} else {
			parentTransactions = VirtualDatabase.PARENT_TRANSACTIONS.values().stream().collect(Collectors.toList());
		}
		if (offset + size < parentTransactions.size()) {
			return parentTransactions.subList(offset, offset + size);
		} else {
			return parentTransactions.subList(offset, parentTransactions.size());
		}
	}

	public int getParentTransactionsSize() {
		return VirtualDatabase.PARENT_TRANSACTIONS.size();
	}

	/**
	 * 
	 * @param parentTransactions
	 * @return
	 */
	private List<ParentTransaction> deepCopy(Map<Integer, ParentTransaction> parentTransactions) {
		List<ParentTransaction> parentTransactionsDeepCopy = new ArrayList<>();
		for (ParentTransaction transaction : parentTransactions.values()) {
			parentTransactionsDeepCopy.add(new ParentTransaction(transaction));
		}
		return parentTransactionsDeepCopy;
	}
}
