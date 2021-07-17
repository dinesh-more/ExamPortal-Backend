package com.exam;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ExamServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started...");

        User user = new User();
        user.setFirstname("Dinesh");
        user.setLastname("More");
        user.setUsername("dinesh22");
        user.setPassword(bCryptPasswordEncoder.encode("Admin"));
        user.setEmail("dinesh@email.com");
        user.setProfile("default.png");
        user.setPhone("8805512261");

        Role role = new Role(); // role.setRoleId(111); role.setRoleName("admin");
        role.setRoleName("Admin");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        this.userService.createUser(user, userRoles);
		System.out.println("HHH: "+user.toString());

    }
}
