package io.github.zhyshko.facade.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.zhyshko.dto.Logs;
import io.github.zhyshko.dto.RoomAccess;
import io.github.zhyshko.dto.RoomStatus;
import io.github.zhyshko.dto.Stats;
import io.github.zhyshko.facade.RoomFacade;
import io.github.zhyshko.model.Room;
import io.github.zhyshko.model.User;
import io.github.zhyshko.service.LogsService;
import io.github.zhyshko.service.RoomService;
import io.github.zhyshko.service.StatsService;
import io.github.zhyshko.service.UserService;

@Component
public class DefaultRoomFacade implements RoomFacade {

	@Autowired
	private RoomService roomService;

	@Autowired
	private UserService userService;

	@Autowired
	private StatsService statsService;

	@Autowired
    private LogsService logsService;

	@Override
	public void saveNewData(RoomStatus roomStatus) {
		Room room;
		try {
			room = roomService.getById(roomStatus.getRoomId());
			room.setAirPollution(roomStatus.getAirPollution());
			room.setLastUpdatedTime(LocalDateTime.now());
		} catch (NullPointerException e) {
			room = Room.builder().id(roomStatus.getRoomId()).airPollution(roomStatus.getAirPollution()).lastUpdatedTime(LocalDateTime.now()).build();
		}
		statsService.saveStat(room.getId(), room.getAirPollution());
		roomService.saveRoom(room);
	}

	@Override
	public boolean userCardRequest(RoomAccess roomAccessData) {
	    boolean userAccessGranted = checkUserAccess(roomAccessData);
	    User user = userService.getById(roomAccessData.getUserId());
	    String message = prepareRoomAccessLogMessage(userAccessGranted, user.getUsername());
	    logsService.saveLog(roomAccessData.getRoomId(), message);
		return userAccessGranted;
	}

	private String prepareRoomAccessLogMessage(boolean userAccessGranted, String username) {
        return username+"|"+(userAccessGranted?"GRANTED":"REJECTED");
    }

    private boolean checkUserAccess(RoomAccess roomAccessData) {
		Room room = roomService.getById(roomAccessData.getRoomId());
		User user = userService.getById(roomAccessData.getUserId());
		return room.getAllowedUsers() != null && room.getAllowedUsers().contains(user);
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public RoomStatus prepareRoomStatus(long roomId) {
		Room room = roomService.getById(roomId);
		return RoomStatus.builder()
				.roomId(roomId)
				.airPollution(room.getAirPollution())
				.lastUpdatedTime(room.getLastUpdatedTime())
				.build();
	}

	@Override
	public Stats getLastNRoomStats(long roomId, int entriesCount) {
		return statsService.getStats(roomId).getlastNStats(entriesCount);
	}

    @Override
    public Logs getLastNRoomLogs(long roomId, int entriesCount) {
        return logsService.getLogs(roomId).getlastNLogs(entriesCount);
    }

}
