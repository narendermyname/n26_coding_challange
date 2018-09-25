/**
 * 
 */
package com.n26.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.dto.Statistic;
import com.n26.repository.StatisticRepository;

/**
 * @author natanwar
 *
 */
@Service
public class StatisticService {

	@Autowired
	private StatisticRepository statisticRepository;

	public Statistic getStatistics() {
		return statisticRepository.getStatistics();
	}
}
