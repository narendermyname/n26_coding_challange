/**
 * 
 */
package com.n26.service;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.n26.dto.Statistic;
import com.n26.repository.StatisticRepository;

/**
 * @author natanwar
 *
 */
public class TrnStatisticServiceTest {

	private StatisticService trnStatisticService;

	@Before
	public void setup() {
		trnStatisticService = new StatisticService();

		StatisticRepository repository = EasyMock.createMock(StatisticRepository.class);

		EasyMock.expect(repository.getStatistics())
				.andReturn(new Statistic("1000.00", "100.53", "200000.49", "50.23", 10)).anyTimes();
		EasyMock.replay(repository);

		ReflectionTestUtils.setField(trnStatisticService, "statisticRepository", repository);
	}

	@Test
	public void statisticsTest() {
		Statistic statistic = trnStatisticService.getStatistics();
		assertEquals(statistic.getSum(), "1000.00");
		assertEquals(statistic.getAvg(), "100.53");
		assertEquals(statistic.getMax(), "200000.49");
		assertEquals(statistic.getMin(), "50.23");
		assertEquals(statistic.getCount(), 10);
	}
}
