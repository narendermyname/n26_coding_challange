package com.n26.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TransactionControllerLiveTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void addTrnShouldReturnCreatedStatus() throws Exception {
		Instant instant = Instant.now();
		ZonedDateTime dateTime = instant.atZone(ZoneId.of("GMT+00:00"));
		String request = "{\"amount\": \"12.3343\",\"timestamp\": \"" + dateTime.toInstant().toString() + "\"}";
		this.mockMvc
				.perform(
						post("/transactions")
								.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
										MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
								.content(request))
				.andExpect(status().isCreated());
	}

	@Test
	public void addTrnShouldReturnUnProcessableStatusWhenDataNotParsable() throws Exception {
		String request = "{\"amount\": \"12.3343u\",\"timestamp\": \"2018-29-29T09:59:51.312Z\"}";
		this.mockMvc
				.perform(
						post("/transactions")
								.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
										MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
								.content(request))
				.andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void addTrnShouldReturnNoContentWhenTimestampOlderThan60Second() throws Exception {
		String request = "{\"amount\": \"12.3343\",\"timestamp\": \"2018-07-17T09:59:51.312Z\"}";
		this.mockMvc
				.perform(
						post("/transactions")
								.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
										MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
								.content(request))
				.andExpect(status().isNoContent());
	}

	@Test
	public void deleteTrnTestShouldReturnNoContent() throws Exception {
		String request = "{\"amount\": \"12.3343\",\"timestamp\": \"2018-07-17T09:59:51.312Z\"}";
		this.mockMvc
				.perform(
						post("/transactions")
								.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
										MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
								.content(request))
				.andExpect(status().isNoContent());

		this.mockMvc.perform(delete("/transactions")).andExpect(status().isNoContent());
	}

}
