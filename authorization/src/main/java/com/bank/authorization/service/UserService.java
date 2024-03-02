package com.bank.authorization.service;

import com.bank.authorization.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Users findByRole(String role);

    List<Users> getAllUsers();

    Users findById(Byte id);

    void createOrUpdateUser(Users user);

    void deleteUser(Byte id);
}
