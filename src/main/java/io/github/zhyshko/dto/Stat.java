package io.github.zhyshko.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stat {

	private int pollution;
	private LocalDateTime dateTime;

}
