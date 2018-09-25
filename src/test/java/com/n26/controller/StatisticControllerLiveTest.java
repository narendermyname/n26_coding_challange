/**
 * 
 */
package com.n26.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.n26.dto.Statistic;
import com.n26.service.StatisticService;

/**
 * @author natanwar
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class StatisticControllerLiveTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatisticService service;

	@Test
	public void testStatisticWithMockvalue() throws Exception {
		when(service.getStatistics()).thenReturn(new Statistic("2.00", "1.00", "1.00", "1.00", 2));

		mockMvc.perform(get("/statistics")).andExpect(status().isOk()).andExpect(content().string(
				containsString("{\"sum\":\"2.00\",\"avg\":\"1.00\",\"max\":\"1.00\",\"min\":\"1.00\",\"count\":2}")));
	}
}
