/**
 * 
 */
package com.n26.validator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.n26.data.store.TransactionDataStore;
import com.n26.dto.TransactionData;
import com.n26.dto.builder.TransactionDataBuilder;

/**
 * 
 * @author natanwar
 *
 */
public class TransactionDataValidatorTest {

	private TransactionDataValidator validator;

	@Before
	public void setup() {
		validator = new TransactionDataValidator();
	}

	@Test
	public void testValidTransactionData() {
		ZonedDateTime currentDate = getCurrentTime();
		TransactionData trn = new TransactionDataBuilder().withAmount(new BigDecimal("12321.22"))
				.withTimeStamp(currentDate).build();
		HttpStatus status = validator.validateAndReturnStatus(trn);

		assertEquals(HttpStatus.CREATED, status);
	}

	@Test
	public void testFutureDateInTransactionData() {
		ZonedDateTime currentDate = getCurrentTime();
		TransactionData trn = new TransactionDataBuilder().withAmount(new BigDecimal("12321.22"))
				.withTimeStamp(currentDate.plusMinutes(5)).build();
		HttpStatus status = validator.validateAndReturnStatus(trn);

		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, status);
	}

	@Test
	public void testOldeTransactionData() {
		ZonedDateTime currentDate = getCurrentTime();

		TransactionData trn = new TransactionDataBuilder().withAmount(new BigDecimal("12321.22"))
				.withTimeStamp(currentDate.minusMinutes(5)).build();
		HttpStatus status = validator.validateAndReturnStatus(trn);

		assertEquals(HttpStatus.NO_CONTENT, status);
	}

	private ZonedDateTime getCurrentTime() {
		return Instant.now().atZone(ZoneId.of(TransactionDataStore.UTC_TIMEZONE));
	}
}
