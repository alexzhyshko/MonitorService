package io.github.zhyshko.controllers.mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.dto.Log;
import io.github.zhyshko.dto.Logs;
import io.github.zhyshko.dto.RoomStatus;
import io.github.zhyshko.dto.Stat;
import io.github.zhyshko.dto.Stats;

@RestController
@ConditionalOnExpression("${mock.website.controller:true}")
public class MockWebsiteController {

    @GetMapping(value = "/mockStatus")
    @ResponseStatus(HttpStatus.OK)
    public RoomStatus getRoomStatus(HttpServletResponse res, @RequestParam long roomId) {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Content-Type", "application/json");
        return prepareMockRoomStatus();
    }

    @GetMapping(value = "/mockRoomStats")
    @ResponseStatus(HttpStatus.OK)
    public Stats getRoomStats(HttpServletResponse res, @RequestParam long roomId, @RequestParam int entriesCount) {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Content-Type", "application/json");
        return prepareMockStats();
    }

    @GetMapping(value = "/mockRoomLogs")
    @ResponseStatus(HttpStatus.OK)
    public Logs getRoomLogs(HttpServletResponse res, @RequestParam long roomId, @RequestParam int entriesCount) {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Content-Type", "application/json");
        return prepareMockLogs();
    }

    private RoomStatus prepareMockRoomStatus() {
        return RoomStatus.builder().airPollution(135).lastUpdatedTime(LocalDateTime.now()).roomId(522).build();
    }

    private Stats prepareMockStats() {
        return Stats.builder().stats(prepareMockStatsList()).build();
    }

    private Logs prepareMockLogs() {
        return Logs.builder().logs(prepareMockLogsList()).build();
    }

    private List<Stat> prepareMockStatsList() {
        LocalDateTime currentTime = LocalDateTime.now();
        return Arrays.asList(
                prepareMockStat(100, currentTime.minusMinutes(10)),
                prepareMockStat(140, currentTime.minusMinutes(9)),
                prepareMockStat(120, currentTime.minusMinutes(8)),
                prepareMockStat(110, currentTime.minusMinutes(7)),
                prepareMockStat(115, currentTime.minusMinutes(6)),
                prepareMockStat(145, currentTime.minusMinutes(5)),
                prepareMockStat(130, currentTime.minusMinutes(4)),
                prepareMockStat(125, currentTime.minusMinutes(3)),
                prepareMockStat(115, currentTime.minusMinutes(2)),
                prepareMockStat(95, currentTime.minusMinutes(1)),
                prepareMockStat(135, currentTime));
    }

    private List<Log> prepareMockLogsList() {
        LocalDateTime currentTime = LocalDateTime.now();
        return Arrays.asList(
                prepareMockLog("alexzhyshko1|GRANTED", currentTime.minusMinutes(10)),
                prepareMockLog("alexzhyshko2|REJECTED", currentTime.minusMinutes(9)),
                prepareMockLog("alexzhyshko3|REJECTED", currentTime.minusMinutes(8)),
                prepareMockLog("alexzhyshko4|GRANTED", currentTime.minusMinutes(7)),
                prepareMockLog("alexzhyshko5|GRANTED", currentTime.minusMinutes(6)),
                prepareMockLog("alexzhyshko6|REJECTED", currentTime.minusMinutes(5)),
                prepareMockLog("alexzhyshko7|REJECTED", currentTime.minusMinutes(4)),
                prepareMockLog("alexzhyshko8|REJECTED", currentTime.minusMinutes(3)),
                prepareMockLog("alexzhyshko9|GRANTED", currentTime.minusMinutes(2)),
                prepareMockLog("alexzhyshko10|GRANTED", currentTime.minusMinutes(1)),
                prepareMockLog("alexzhyshko11|REJECTED", currentTime));
    }

    private Stat prepareMockStat(int pollution, LocalDateTime dateTime) {
        return Stat.builder().pollution(pollution).dateTime(dateTime).build();
    }

    private Log prepareMockLog(String message, LocalDateTime dateTime) {
        return Log.builder().message(message).dateTime(dateTime).build();
    }
}
