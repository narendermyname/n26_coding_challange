/**
 * 
 */
package com.n26.parser;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.n26.dto.TransactionData;
import com.n26.dto.TransactionRequest;
import com.n26.exception.ParseException;
import com.n26.parser.TransactionDataParser;

/**
 * @author natanwar
 *
 */
public class TransactionDataParserTest {

	private TransactionDataParser parser;

	@Before
	public void setup() {
		parser = new TransactionDataParser();
	}

	@Test
	public void testTransactionData() throws ParseException {
		TransactionRequest trn = new TransactionRequest("12321.22", "2018-07-17T09:59:51.312Z");
		TransactionData trnData = parser.praseRequest(trn);
		assertEquals(trnData.getAmount(), new BigDecimal(12321.22).setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	@Test(expected = ParseException.class)
	public void testInvalidAmountTransactionData() throws ParseException {
		TransactionRequest trn = new TransactionRequest("12321.22u", "2018-07-08T09:59:51.312Z");
		parser.praseRequest(trn);
	}

	@Test(expected = ParseException.class)
	public void testInvalidDateTransactionData() throws ParseException {
		TransactionRequest trn = new TransactionRequest("12321.22", "2018-29-29T09:59:51.312Z");
		parser.praseRequest(trn);
	}
}
