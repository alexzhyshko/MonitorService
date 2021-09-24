package io.github.zhyshko.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomStatus {

	private long roomId;
	private int airPollution;
	private int peopleInsideCount;
	private LocalDateTime lastUpdatedTime;

}
