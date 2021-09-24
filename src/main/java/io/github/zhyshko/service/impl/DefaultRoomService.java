package io.github.zhyshko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.zhyshko.dao.RoomRepository;
import io.github.zhyshko.model.Room;
import io.github.zhyshko.service.RoomService;

@Service
public class DefaultRoomService implements RoomService{

	@Autowired
	private RoomRepository roomRepository;


	public Room getById(long id) {
		return roomRepository.findById(id).orElseThrow(()->new NullPointerException("No room is found for id "+id));
	}

	public void saveRoom(Room room) {
		roomRepository.save(room);
	}

	public void setRoomRepository(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

}
