/**
 * 
 */
package com.n26.data.store;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.n26.dto.Statistic;
import com.n26.dto.TransactionData;
import com.n26.dto.builder.TransactionDataBuilder;

/**
 * @author natanwar
 *
 */
public class TransactionDataStoreTest {

	private TransactionDataStore dataStore;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataStore = new TransactionDataStore();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.n26.data.store.TransactionDataStore#save(com.n26.dto.TransactionRequest)}.
	 */
	@Test
	public void testSave() {

		ZonedDateTime currentDate = getCurrentTime();
		BigDecimal amount = new BigDecimal("12321.22");
		TransactionData trn = new TransactionDataBuilder().withAmount(amount).withTimeStamp(currentDate).build();
		dataStore.save(trn);
		TransactionData persistedTrn = dataStore.findAll().get(0);
		assertEquals(persistedTrn.getAmount(), amount);
		assertEquals(persistedTrn.getDateTime(), currentDate);
	}

	/**
	 * Test method for {@link com.n26.data.store.TransactionDataStore#findAll()}.
	 */
	@Test
	public void testFindAll() {
		ZonedDateTime currentDate = getCurrentTime();
		BigDecimal amount = new BigDecimal("12321.22");
		TransactionData trn = new TransactionDataBuilder().withAmount(amount).withTimeStamp(currentDate).build();
		dataStore.save(trn);
		trn = new TransactionDataBuilder().withAmount(amount.add(BigDecimal.valueOf(123))).withTimeStamp(currentDate)
				.build();
		dataStore.save(trn);
		assertEquals(dataStore.findAll().size(), 2);
	}

	/**
	 * Test method for {@link com.n26.data.store.TransactionDataStore#deleteAll()}.
	 */
	@Test
	public void testDeleteAll() {
		BigDecimal amount = new BigDecimal("12321.22");
		TransactionData trn = new TransactionDataBuilder().withAmount(amount).withTimeStamp(getCurrentTime()).build();
		dataStore.save(trn);

		dataStore.deleteAll();
		assertEquals(dataStore.findAll().size(), 0);
	}

	@Test
	public void testStatistic() {
		BigDecimal amount = new BigDecimal("5.0");
		TransactionData trn = new TransactionDataBuilder().withAmount(amount).withTimeStamp(getCurrentTime()).build();

		dataStore.save(trn);
		amount = new BigDecimal("5.0");
		trn = new TransactionDataBuilder().withAmount(amount).withTimeStamp(getCurrentTime()).build();

		dataStore.save(trn);

		Statistic statistic = dataStore.getStatistics();

		assertEquals(statistic.getCount(), 2);
	}

	private ZonedDateTime getCurrentTime() {
		return Instant.now().atZone(ZoneId.of("GMT+00:00"));
	}

}
