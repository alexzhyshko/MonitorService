package io.github.zhyshko.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Log {

    private String message;
    private LocalDateTime dateTime;

}

