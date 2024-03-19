package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.dto.UserDTO;
import com.capstone.onlinemoviebooking.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String userName);
    public void creat(UserDTO userDTO);
    public User findUserByEmail(String email);
    public User findUserByName(String name);
}