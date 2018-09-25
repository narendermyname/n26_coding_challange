/**
 * 
 */
package com.n26.data.store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.n26.dto.Statistic;
import com.n26.dto.TransactionData;

/**
 * @author natanwar
 *
 */
@Component
public class TransactionDataStore {

	private CopyOnWriteArrayList<TransactionData> dataStore;

	private static final long ONE_MIN = 60 * 1000;

	public static final String UTC_TIMEZONE = "GMT+00:00";

	public TransactionDataStore() {
		dataStore = new CopyOnWriteArrayList<>();
	}

	public void save(TransactionData transactionData) {
		dataStore.add(transactionData);
	}

	/**
	 * Return a copy of data store
	 * 
	 * @return
	 */
	public List<TransactionData> findAll() {
		return dataStore.stream().map(t -> new TransactionData(t)).collect(Collectors.toList());
	}

	public void deleteAll() {
		dataStore.clear();
	}

	/**
	 * This method computed statistics on the transactions within the last 60
	 * seconds.
	 * 
	 * @return statistics;
	 */
	public Statistic getStatistics() {
		Statistic statistic = new Statistic("0.00", "0.00", "0.00", "0.00", 0);
		List<TransactionData> trnDataList = findAll();

		if (!CollectionUtils.isEmpty(trnDataList)) {
			ZonedDateTime currentDateTime = Instant.now().atZone(ZoneId.of(UTC_TIMEZONE));

			List<TransactionData> responseDataList = trnDataList.stream().filter(trnData -> {
				return ChronoUnit.MILLIS.between(trnData.getDateTime(), currentDateTime) < ONE_MIN;
			}).collect(Collectors.toList());

			double sum = responseDataList.parallelStream().mapToDouble(t -> t.getAmount().doubleValue()).sum();
			OptionalDouble avg = responseDataList.parallelStream().mapToDouble(t -> t.getAmount().doubleValue())
					.average();
			OptionalDouble max = responseDataList.parallelStream().mapToDouble(t -> t.getAmount().doubleValue()).max();
			OptionalDouble min = responseDataList.parallelStream().mapToDouble(t -> t.getAmount().doubleValue()).min();

			statistic = new Statistic(round(sum), round(avg.getAsDouble()), round(max.getAsDouble()),
					round(min.getAsDouble()), responseDataList.size());
		}
		return statistic;
	}

	private String round(double value) {
		return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).toString();
	}
}
