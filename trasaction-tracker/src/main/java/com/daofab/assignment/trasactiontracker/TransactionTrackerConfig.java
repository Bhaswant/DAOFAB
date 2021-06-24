package com.daofab.assignment.trasactiontracker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Config class which helps in context initialization from multiple packages
 * mentioned in ComponentScan
 * 
 * @author i0b00j8
 *
 */
@Configuration
@ComponentScan({ "com.daofab.assignment.transactiontracker.helper", "com.daofab.assignment.transactiontracker.service",
		"com.daofab.assignment.transactiontracker.repository" })
public class TransactionTrackerConfig {

}
