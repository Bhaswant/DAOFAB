package com.daofab.assignment.transactiontracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daofab.assignment.transactiontracker.helper.TransactionTrackerConstants;
import com.daofab.assignment.transactiontracker.pojo.ChildTransaction;
import com.daofab.assignment.transactiontracker.pojo.ITransaction;
import com.daofab.assignment.transactiontracker.pojo.ParentTransaction;
import com.daofab.assignment.transactiontracker.pojo.TransactionResponse;
import com.daofab.assignment.transactiontracker.repository.ChildTransactionRepository;
import com.daofab.assignment.transactiontracker.repository.ParentTransactionRepository;

/**
 * 
 * @author i0b00j8
 *
 */
@Service
public class TransactionTrackerService {

	@Autowired
	private ParentTransactionRepository parentRepo;

	@Autowired
	private ChildTransactionRepository childRepo;

	public TransactionResponse getParentTransactions(final Integer pageNo, final Integer pageSize, final String sortCol) {
		int offset = getOffset(pageNo, pageSize);
		List<ParentTransaction> parentTransactions = parentRepo.getParentTransaction(offset, pageSize, sortCol);
		return constructTransactionResponse(parentTransactions, pageNo, pageSize, sortCol,
				parentRepo.getParentTransactionsSize());
	}

	/**
	 * For all default implementations
	 * @return
	 */
	public TransactionResponse getParentTransactions() {
		return getParentTransactions(1, TransactionTrackerConstants.DEFAULT_PAGE_SIZE, null);
	}

	public TransactionResponse getChildTransactions(final int parentId) {
		List<ChildTransaction> childTransaction = childRepo.getChildTransactions(parentId);
		return constructTransactionResponse(childTransaction, 1, childTransaction.size(), "id", childTransaction.size());
	}

	private TransactionResponse constructTransactionResponse(final List<? extends ITransaction> transactions,
			final int pageNo, final int pageSize, final String sortCol, final int totalsize) {
		TransactionResponse response = new TransactionResponse(transactions);
		response.setPageSize(pageSize);
		response.setPageNo(pageNo);
		response.setSortCol(sortCol);
		response.setTotalSize(totalsize);
		return response;
	}

	private int getOffset(final int pageNo, final int pageSize) {
		int originalOffset = (pageNo - 1) * pageSize;
		if (originalOffset > parentRepo.getParentTransactionsSize()) {
			return 0;
		}
		return originalOffset;
	}
}
