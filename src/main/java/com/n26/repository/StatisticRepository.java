/**
 * 
 */
package com.n26.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.n26.data.store.TransactionDataStore;
import com.n26.dto.Statistic;

/**
 * @author natanwar
 *
 */

@Repository
public class StatisticRepository {

	@Autowired
	private TransactionDataStore transactionDataStore;

	public Statistic getStatistics() {
		return transactionDataStore.getStatistics();
	}

}
