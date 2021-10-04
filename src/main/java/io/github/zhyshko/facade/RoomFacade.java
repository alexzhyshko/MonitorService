package io.github.zhyshko.facade;

import io.github.zhyshko.dto.RoomAccess;
import io.github.zhyshko.dto.RoomStatus;

public interface RoomFacade {

	void saveNewData(RoomStatus roomStatus);
	boolean userCardRequest(RoomAccess roomAccessData);
	RoomStatus prepareRoomStatus(long roomId);


}
