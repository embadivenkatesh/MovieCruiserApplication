package com.stackroute.moviecruiser.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiser.exceptions.UserAlreadyExistsException;
import com.stackroute.moviecruiser.exceptions.UserNotFoundException;
import com.stackroute.moviecruiser.model.User;
import com.stackroute.moviecruiser.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDao;

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> userList = userDao.findById(user.getUserId());
		if (userList.isPresent()) {
			throw new UserAlreadyExistsException("User already Exists");
		}
		userDao.save(user);
		return true;
	}
	
	public UserServiceImpl(UserRepository userDao) {
		super();
		this.userDao=userDao;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userDao.findByUserIdAndPassword(userId, password);
		if (user == null) {
			throw new UserNotFoundException("User id and password mismatch");

		}
		return user;
	}

}
