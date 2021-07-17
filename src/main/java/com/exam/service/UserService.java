package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoleSet) throws Exception;

    public User getUser(String userName) throws Exception;

    public void updateUser(User user) throws Exception;

}
