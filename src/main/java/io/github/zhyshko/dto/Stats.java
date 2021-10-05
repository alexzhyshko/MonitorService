package io.github.zhyshko.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stats {

	private List<Stat> stats;

	public Stats(int pollution, LocalDateTime dateTime) {
		this.stats = new ArrayList<>();
		this.addStat(pollution, dateTime);
	}

	public void addStat(int pollution, LocalDateTime dateTime) {
		stats.add(Stat.builder().pollution(pollution).dateTime(dateTime).build());
	}

	public Stats getlastNStats(int entriesQuantity) {
		return new Stats(this.stats.stream().limit(entriesQuantity).collect(Collectors.toList()));
	}

}
