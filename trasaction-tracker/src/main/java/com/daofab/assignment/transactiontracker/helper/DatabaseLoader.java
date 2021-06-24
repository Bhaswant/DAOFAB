package com.daofab.assignment.transactiontracker.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.daofab.assignment.transactiontracker.pojo.ChildTransaction;
import com.daofab.assignment.transactiontracker.pojo.ParentTransaction;
import com.daofab.assignment.transactiontracker.pojo.VirtualDatabase;
import com.daofab.assignment.transactiontracker.repository.ParentTransactionRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Ideally, we need to use a DB like MySQL. But for the sake of assignment we are
 * using {@link ParentTransactionRepository}. This class loads the DB entries to
 * in-memory.
 * 
 * Assumptions: 1. Paid amount and id will be in the order int
 * 
 * @author i0b00j8
 *
 */
@Component
public class DatabaseLoader {

	private static Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

	private static final Gson GSON = new Gson();
	
	/**
	 * Private constructor to avoid instantiation 
	 */
	private DatabaseLoader() {
	}

	public static void load(final String parentJsonPath, final String childJsonPath) {
		String parentJson = null;
		String childJson = null;
		try {
			parentJson = IOUtils.toString(new FileInputStream(parentJsonPath), Charset.defaultCharset());
			childJson = IOUtils.toString(new FileInputStream(childJsonPath), Charset.defaultCharset());
		} catch (IOException e) {
			// Exiting the program as seed data itself is incorrect.
			logger.error("Exception occured while loading the file, exiting.", e);
			System.exit(-1);
		}
		fillParents(parentJson);
		fillChildren(childJson);
	}

	/**
	 * 
	 * @param parentTransactionList
	 * @param childJson
	 */
	private static void fillChildren(final String childJson) {
		JsonArray childData = GSON.fromJson(childJson, JsonObject.class).get("data").getAsJsonArray();
		for (int i = 0; i < childData.size(); i++) {
			JsonObject child = childData.get(i).getAsJsonObject();
			int id = child.get("id").getAsInt();
			int parentId = child.get("parentId").getAsInt();
			int paidAmount = child.get("paidAmount").getAsInt();

			ParentTransaction parentTransaction = VirtualDatabase.PARENT_TRANSACTIONS.get(parentId);
			if (parentTransaction == null) {
				logger.error("No parent info available for child: {}", id);
			}
			ChildTransaction transaction = new ChildTransaction(parentTransaction, id, paidAmount);

			VirtualDatabase.CHILD_TRANSACTIONS.put(id, transaction);
			if (!VirtualDatabase.PARENT_CHILD_MAPPING.containsKey(parentId)) {
				VirtualDatabase.PARENT_CHILD_MAPPING.put(parentId, new TreeSet<>());
			}
			VirtualDatabase.PARENT_CHILD_MAPPING.get(parentId).add(id);
		}
	}

	/**
	 * Processes parent json and creates ParentTransaction classes
	 * 
	 * @param parentJson
	 * @return
	 */
	private static void fillParents(final String parentJson) {
		JsonArray parentsData = GSON.fromJson(parentJson, JsonObject.class).get("data").getAsJsonArray();
		for (int i = 0; i < parentsData.size(); i++) {
			JsonObject parent = parentsData.get(i).getAsJsonObject();
			int id = parent.get("id").getAsInt();
			String sender = parent.get("sender").getAsString();
			String receiver = parent.get("receiver").getAsString();
			int totalAmount = parent.get("totalAmount").getAsInt();
			ParentTransaction parentTransaction = new ParentTransaction(id, sender, receiver, totalAmount);
			VirtualDatabase.PARENT_TRANSACTIONS.put(id, parentTransaction);
		}
	}
}
