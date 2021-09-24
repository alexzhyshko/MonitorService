package io.github.zhyshko.service;

import io.github.zhyshko.model.Room;

public interface RoomService {

	Room getById(long id);
	void saveRoom(Room room);

}
