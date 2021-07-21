package com.exam.service.Impl;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User localUser = this.userRepository.findByUsername(user.getUsername());
		if (localUser != null) {
			System.out.println("User is already present.");
			throw new Exception("User already present.");
		} else {
			// create user
			for (UserRole ur : userRoles) {
				this.roleRepository.save(ur.getRole());
			}

			user.getUserRoles().addAll(userRoles);
			localUser = this.userRepository.save(user);
		}
		return localUser;
	}

	@Override
	public User getUser(String userName) {
		return this.userRepository.findByUsername(userName);
	}

	@Override
	public void updateUser(User user) throws Exception {
//        User existingUser = this.userRepository.findByuserName(user.getUserName());
		User existingUser = this.userRepository.findById(user.getId()).get(); // .get() -> if exist.
		existingUser.setFirstname(user.getFirstname());
		existingUser.setLastname(user.getLastname());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setPhone(user.getPhone());
		existingUser.setProfile(user.getProfile());
		this.userRepository.save(existingUser);
	}

}
