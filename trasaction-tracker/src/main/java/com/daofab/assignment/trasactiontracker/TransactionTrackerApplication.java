package com.daofab.assignment.trasactiontracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.daofab.assignment.transactiontracker.helper.DatabaseLoader;

@SpringBootApplication
public class TransactionTrackerApplication {

	private static Logger logger = LoggerFactory.getLogger(TransactionTrackerApplication.class);

	public static void main(String[] args) {
		if (args.length != 2) {
			logger.error("Two arguments required. 1. Parent Json, 2. Child Json");
			return;
		}
		DatabaseLoader.load(args[0], args[1]);
		SpringApplication.run(TransactionTrackerApplication.class, args);
	}
}