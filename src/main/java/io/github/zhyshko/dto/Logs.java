package io.github.zhyshko.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Logs {

    private List<Log> logs;

    public Logs(String message, LocalDateTime dateTime) {
        this.logs = new ArrayList<>();
        this.addLog(message, dateTime);
    }

    public void addLog(String message, LocalDateTime dateTime) {
        logs.add(Log.builder().message(message).dateTime(dateTime).build());
    }

    public Logs getlastNLogs(int entriesQuantity) {
        return new Logs(this.logs.stream().limit(entriesQuantity).collect(Collectors.toList()));
    }

}
