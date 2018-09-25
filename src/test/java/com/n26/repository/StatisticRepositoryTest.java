/**
 * 
 */
package com.n26.repository;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.n26.data.store.TransactionDataStore;
import com.n26.dto.Statistic;

/**
 * @author natanwar
 *
 */
public class StatisticRepositoryTest {

	private StatisticRepository statisticRepo;

	@Before
	public void setup() {
		statisticRepo = new StatisticRepository();
		TransactionDataStore dataStore = EasyMock.createMock(TransactionDataStore.class);
		EasyMock.expect(dataStore.getStatistics()).andReturn(new Statistic("3.0", "1.0", "1.0", "1.0", 3));
		EasyMock.replay(dataStore);
		ReflectionTestUtils.setField(statisticRepo, "transactionDataStore", dataStore);
	}

	@Test
	public void testStatistics() {
		Statistic statistic = statisticRepo.getStatistics();
		assertEquals(statistic.getSum(), "3.0");
		assertEquals(statistic.getAvg(), "1.0");
		assertEquals(statistic.getMax(), "1.0");
		assertEquals(statistic.getMin(), "1.0");
		assertEquals(statistic.getCount(), 3);
	}
}
