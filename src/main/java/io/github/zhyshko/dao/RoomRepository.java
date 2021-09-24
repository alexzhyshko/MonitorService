package io.github.zhyshko.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{

}
