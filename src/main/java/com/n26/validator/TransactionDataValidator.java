/**
 * 
 */
package com.n26.validator;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.n26.data.store.TransactionDataStore;
import com.n26.dto.TransactionData;
import com.n26.exception.TransactionException;

/**
 * @author natanwar
 *
 */
@Component
public class TransactionDataValidator {

	public void validate(TransactionData trn) {
		ZonedDateTime currentTime = Instant.now().atZone(ZoneId.of(TransactionDataStore.UTC_TIMEZONE));
		if (isTrnDateFutureDate(trn, currentTime)) {
			throw new TransactionException(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		if (isTrnOlderThan60Second(trn, currentTime)) {
			throw new TransactionException(HttpStatus.NO_CONTENT);
		}
	}

	private boolean isTrnDateFutureDate(TransactionData trn, ZonedDateTime currentDateTime) {
		return ChronoUnit.MILLIS.between(trn.getDateTime(), currentDateTime) < 0;
	}

	private boolean isTrnOlderThan60Second(TransactionData trn, ZonedDateTime currentDateTime) {
		return ChronoUnit.MILLIS.between(trn.getDateTime(), currentDateTime) > 60000;
	}
}
