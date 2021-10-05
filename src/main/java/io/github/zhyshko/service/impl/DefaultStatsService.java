package io.github.zhyshko.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import io.github.zhyshko.dto.Stats;
import io.github.zhyshko.service.StatsService;

public class DefaultStatsService implements StatsService{

	private Map<Long, Stats> stats = new HashMap<>();

	@Override
	public void saveStat(long roomId, int airPollution) {
		if(stats.containsKey(roomId)) {
			stats.get(roomId).addStat(airPollution, LocalDateTime.now());
		}else {
			stats.put(roomId, new Stats(airPollution, LocalDateTime.now()));
		}
	}

	@Override
	public Stats getStats(long roomId) {
		return stats.get(roomId);
	}

}
