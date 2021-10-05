package io.github.zhyshko.service;

import io.github.zhyshko.dto.Stats;

public interface StatsService {

	void saveStat(long roomId, int airPollution);
	Stats getStats(long roomId);

}
