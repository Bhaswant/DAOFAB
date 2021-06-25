package com.daofab.assignment.transactiontracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.assignment.transactiontracker.helper.TransactionTrackerConstants;
import com.daofab.assignment.transactiontracker.pojo.TransactionResponse;
import com.daofab.assignment.transactiontracker.service.TransactionTrackerService;

/**
 * Controller class acting as an interface for all APIs from the user and to
 * that are related to Transactions.
 * 
 * @author i0b00j8
 *
 */
@RestController
@RequestMapping("/v1/transactions")
public class TransactionTrackerController {

	private Logger logger = LoggerFactory.getLogger(TransactionTrackerController.class);

	@Autowired
	private TransactionTrackerService service;

	@GetMapping("/ping")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>(TransactionTrackerConstants.OK_MESSAGE, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<TransactionResponse> transactions(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortCol) {
		TransactionResponse transactionResponse;
		try {
			if (pageNo == null) {
				pageNo = 1;
			}
			if (pageSize == null) {
				pageSize = TransactionTrackerConstants.DEFAULT_PAGE_SIZE;
			}
			transactionResponse = service.getParentTransactions(pageNo, pageSize, sortCol);
		} catch (Exception e) {
			logger.error("Error occured in fetching transactions", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
	}

	@GetMapping("/child")
	public ResponseEntity<TransactionResponse> childTransactions(@RequestParam int parentId) {
		TransactionResponse response;
		try {
			response = service.getChildTransactions(parentId);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
