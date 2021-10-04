package io.github.zhyshko.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.dto.RoomAccess;
import io.github.zhyshko.dto.RoomStatus;
import io.github.zhyshko.facade.RoomFacade;

@RestController
public class ArduinoController {

	@Autowired
	private RoomFacade roomFacade;

	@PostMapping(value = "/card")
    public boolean cardEnter(HttpServletResponse res, @RequestBody RoomAccess roomEnterData) {
        return roomFacade.userCardRequest(roomEnterData);
    }

	@PostMapping(value = "/status")
    @ResponseStatus(HttpStatus.OK)
    public void setRoomStatus(HttpServletResponse res, @RequestBody RoomStatus roomData) {
		roomFacade.saveNewData(roomData);
    }
}
