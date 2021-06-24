package com.daofab.assignment.transactiontracker.helper;

import java.util.Comparator;

import com.daofab.assignment.transactiontracker.pojo.ParentTransaction;

/**
 * Comparator for parent transactions. This leaves space for other sorting
 * mechanisms apart from Id.
 * 
 * @author i0b00j8
 *
 */
public interface IParentComparator extends Comparator<ParentTransaction> {

}
