package io.github.zhyshko.service;

import io.github.zhyshko.dto.Logs;

public interface LogsService {

    void saveLog(long roomId, String message);
    Logs getLogs(long roomId);

}
