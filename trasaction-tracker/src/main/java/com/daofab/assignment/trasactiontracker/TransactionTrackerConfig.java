package com.daofab.assignment.trasactiontracker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.daofab.assignment.transactiontracker.helper", "com.daofab.assignment.transactiontracker.service",
		"com.daofab.assignment.transactiontracker.repository" })
public class TransactionTrackerConfig {

}
