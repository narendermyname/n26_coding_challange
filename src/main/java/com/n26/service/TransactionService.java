/**
 * 
 */
package com.n26.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.dto.TransactionData;
import com.n26.repository.TransactionRepository;

/**
 * @author natanwar
 *
 */
@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public void add(TransactionData trnData) {
		transactionRepository.save(trnData);
	}

	public void delete() {
		transactionRepository.deleteAll();
	}
}
