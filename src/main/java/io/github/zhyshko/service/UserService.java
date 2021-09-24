package io.github.zhyshko.service;

import io.github.zhyshko.model.User;

public interface UserService {

	User getById(String id);
	void saveUser(User room);

}
