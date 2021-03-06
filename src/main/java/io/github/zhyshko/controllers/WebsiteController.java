package io.github.zhyshko.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.dto.Logs;
import io.github.zhyshko.dto.RoomStatus;
import io.github.zhyshko.dto.Stats;
import io.github.zhyshko.facade.RoomFacade;

@RestController
@ConditionalOnExpression("${mock.website.controller:false}")
public class WebsiteController {

	@Autowired
	private RoomFacade roomFacade;

	@GetMapping(value = "/status")
    @ResponseStatus(HttpStatus.OK)
    public RoomStatus getRoomStatus(HttpServletResponse res, @RequestParam long roomId) {
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Content-Type", "application/json");
        return roomFacade.prepareRoomStatus(roomId);
    }

	@GetMapping(value = "/roomStats")
    @ResponseStatus(HttpStatus.OK)
    public Stats getRoomStats(HttpServletResponse res, @RequestParam long roomId, @RequestParam int entriesCount) {
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Content-Type", "application/json");
        return roomFacade.getLastNRoomStats(roomId, entriesCount);
    }

	@GetMapping(value = "/roomLogs")
    @ResponseStatus(HttpStatus.OK)
    public Logs getRoomLogs(HttpServletResponse res, @RequestParam long roomId, @RequestParam int entriesCount) {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Content-Type", "application/json");
        return roomFacade.getLastNRoomLogs(roomId, entriesCount);
    }
}
