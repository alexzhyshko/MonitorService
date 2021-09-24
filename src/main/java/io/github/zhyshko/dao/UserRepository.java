package io.github.zhyshko.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.model.User;

@Repository
public interface UserRepository  extends CrudRepository<User, String>{

}
