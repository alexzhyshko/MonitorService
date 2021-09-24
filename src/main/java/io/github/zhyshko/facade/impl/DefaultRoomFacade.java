package io.github.zhyshko.facade.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.zhyshko.dto.RoomAccess;
import io.github.zhyshko.dto.RoomStatus;
import io.github.zhyshko.facade.RoomFacade;
import io.github.zhyshko.model.Room;
import io.github.zhyshko.model.User;
import io.github.zhyshko.service.RoomService;
import io.github.zhyshko.service.UserService;

@Component
public class DefaultRoomFacade implements RoomFacade {

	@Autowired
	private RoomService roomService;

	@Autowired
	private UserService userService;

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
		roomService.saveRoom(room);
	}

	@Override
	public boolean userEnterToRoom(RoomAccess roomAccessData) {
		if (checkUserAccess(roomAccessData) && !userAlreadyInRoom(roomAccessData)) {
			return addUserToRoom(roomAccessData);
		} else {
			return false;
		}
	}

	@Override
	public boolean userExitFromRoom(RoomAccess roomAccessData) {
		if (checkUserAccess(roomAccessData) && userAlreadyInRoom(roomAccessData)) {
			return removeUserFromRoom(roomAccessData);
		} else {
			return false;
		}
	}

	private boolean checkUserAccess(RoomAccess roomAccessData) {
		Room room = roomService.getById(roomAccessData.getRoomId());
		User user = userService.getById(roomAccessData.getUserId());
		return room.getAllowedUsers() != null && room.getAllowedUsers().contains(user);
	}

	private boolean userAlreadyInRoom(RoomAccess roomAccessData) {
		Room room = roomService.getById(roomAccessData.getRoomId());
		User user = userService.getById(roomAccessData.getUserId());
		return user.getCurrentRoom() != null && user.getCurrentRoom().equals(room);
	}

	@Override
	public boolean addUserToRoom(RoomAccess roomEnterData) {
		try {
			Room room = roomService.getById(roomEnterData.getRoomId());
			User user = userService.getById(roomEnterData.getUserId());
			room.getUsersInside().add(user);
			room.setPeopleCount(room.getPeopleCount() + 1);
			user.setCurrentRoom(room);
			roomService.saveRoom(room);
			userService.saveUser(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean removeUserFromRoom(RoomAccess roomExitData) {
		try {
			Room room = roomService.getById(roomExitData.getRoomId());
			User user = userService.getById(roomExitData.getUserId());
			room.getUsersInside().remove(user);
			user.setCurrentRoom(null);
			room.setPeopleCount(room.getPeopleCount() - 1);
			roomService.saveRoom(room);
			userService.saveUser(user);
		} catch (Exception e) {
			return false;
		}
		return true;
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
				.peopleInsideCount(room.getPeopleCount())
				.lastUpdatedTime(room.getLastUpdatedTime())
				.build();
	}

}
