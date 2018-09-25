/**
 * 
 */
package com.n26.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.n26.data.store.TransactionDataStore;
import com.n26.dto.TransactionData;

/**
 * @author natanwar
 *
 */
@Repository
public class TransactionRepository {

	@Autowired
	private TransactionDataStore transactionDataStore;

	public void save(TransactionData trnData) {
		transactionDataStore.save(trnData);
	}

	public void deleteAll() {
		transactionDataStore.deleteAll();
	}
}
