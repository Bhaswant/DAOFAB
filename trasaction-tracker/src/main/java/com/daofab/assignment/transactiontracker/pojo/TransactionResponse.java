package com.daofab.assignment.transactiontracker.pojo;

import java.util.List;

import lombok.Data;

/**
 * Response sent back to the caller (UI/ Rest client)
 * @author i0b00j8
 *
 */
@Data
public class TransactionResponse {
	
	private final List<? extends ITransaction> transactions;
	
	private int pageNo;
	
	private int pageSize;
	
	private int totalSize;
	
	private String sortCol;
}
