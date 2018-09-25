/**
 * 
 */
package com.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.service.StatisticService;

/**
 * @author natanwar
 *
 */
@RestController("/statistics")
public class StatisticController {

	@Autowired
	private StatisticService statisticService;

	@GetMapping
	public ResponseEntity<?> getStatistics() {
		return ResponseEntity.ok(statisticService.getStatistics());
	}

}
