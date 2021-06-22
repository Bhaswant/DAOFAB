package com.daofab.assignment.trasactiontracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.assignment.transactiontracker.helper.TransactionTrackerLog;

@RestController
@RequestMapping("/v1/transactions")
public class TrasactionTrackerController {
	
	@GetMapping(value="/ping")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>(TransactionTrackerLog.OK_MESSAGE, HttpStatus.OK);
	}
	
}
