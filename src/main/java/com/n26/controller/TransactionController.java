/**
 * 
 */
package com.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.dto.TransactionData;
import com.n26.dto.TransactionRequest;
import com.n26.exception.ParseException;
import com.n26.exception.TransactionException;
import com.n26.service.TransactionService;
import com.n26.validator.TransactionDataValidator;

/**
 * @author natanwar
 *
 */
@RestController("/transactions")
public class TransactionController {

	@Autowired
	private TransactionDataValidator trnDataValidator;

	@Autowired
	private com.n26.parser.TransactionDataParser trnDataParser;

	@Autowired
	private TransactionService trnService;

	@PostMapping
	public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest transactionRequest)
			throws ParseException {
		TransactionData trnData = trnDataParser.praseRequest(transactionRequest);
		try {
			trnDataValidator.validate(trnData);
		}catch(TransactionException ex) {
			return new ResponseEntity<>(ex.getStatus());
		}
		trnService.add(trnData);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteTransactions() {
		trnService.delete();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
