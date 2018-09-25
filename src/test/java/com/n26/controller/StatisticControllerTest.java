/**
 * 
 */
package com.n26.controller;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.n26.dto.Statistic;
import com.n26.service.StatisticService;

/**
 * @author natanwar
 *
 */
public class StatisticControllerTest {

	private StatisticController controller;

	@Before
	public void setup() {
		controller = new StatisticController();
		StatisticService service = EasyMock.createMock(StatisticService.class);
		EasyMock.expect(service.getStatistics()).andReturn(new Statistic("1.0", "1.0", "1.0", "1.0", 1));
		EasyMock.replay(service);
		ReflectionTestUtils.setField(controller, "statisticService", service);
	}

	@Test
	public void testStatistic() {
		ResponseEntity<?> responseEntity = controller.getStatistics();
		Statistic body = (Statistic) responseEntity.getBody();
		assertEquals(body.getCount(), 1);
		assertEquals(body.getSum(), "1.0");
		assertEquals(body.getAvg(), "1.0");
		assertEquals(body.getMax(), "1.0");
		assertEquals(body.getMin(), "1.0");
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
}
