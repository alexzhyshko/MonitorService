package io.github.zhyshko.facade;

import io.github.zhyshko.dto.RoomAccess;
import io.github.zhyshko.dto.RoomStatus;
import io.github.zhyshko.dto.Stats;

public interface RoomFacade {

	void saveNewData(RoomStatus roomStatus);
	boolean userEnterToRoom(RoomAccess roomAccessData);
	boolean userExitFromRoom(RoomAccess roomAccessData);
	boolean addUserToRoom(RoomAccess roomEnterData);
	boolean removeUserFromRoom(RoomAccess roomExitData);
	RoomStatus prepareRoomStatus(long roomId);
	Stats getLastNRoomStats(long roomId, int entriesCount);

}
