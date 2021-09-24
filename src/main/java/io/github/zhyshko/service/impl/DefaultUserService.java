package io.github.zhyshko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.zhyshko.dao.UserRepository;
import io.github.zhyshko.model.User;
import io.github.zhyshko.service.UserService;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	private UserRepository userRepository;


	public User getById(String id) {
		return userRepository.findById(id).orElseThrow(()->new NullPointerException("No user is found for id "+id));
	}

	public void saveUser(User room) {
		userRepository.save(room);
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
