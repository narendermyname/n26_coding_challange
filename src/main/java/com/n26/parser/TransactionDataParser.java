/**
 * 
 */
package com.n26.parser;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import com.n26.dto.TransactionData;
import com.n26.dto.TransactionRequest;
import com.n26.exception.ParseException;

/**
 * @author natanwar
 *
 */
@Component
public class TransactionDataParser {

	public TransactionData praseRequest(TransactionRequest trn) throws ParseException {
		try {
			return new TransactionData(new BigDecimal(trn.getAmount()),
					ZonedDateTime.parse(trn.getTimestamp(), DateTimeFormatter.ISO_ZONED_DATE_TIME));
		} catch (NumberFormatException | DateTimeParseException e) {
			throw new ParseException();
		}
	}
}
