package io.github.zhyshko.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.github.zhyshko.dto.Logs;
import io.github.zhyshko.service.LogsService;

@Service
public class DefaultLogsService implements LogsService{

    private Map<Long, Logs> logs = new HashMap<>();

    @Override
    public void saveLog(long roomId, String message) {
        if(logs.containsKey(roomId)) {
            logs.get(roomId).addLog(message, LocalDateTime.now());
        }else {
            logs.put(roomId, new Logs(message, LocalDateTime.now()));
        }
    }

    @Override
    public Logs getLogs(long roomId) {
        return logs.get(roomId);
    }

}
