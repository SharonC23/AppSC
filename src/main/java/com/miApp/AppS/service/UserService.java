package com.miApp.AppS.service;

import com.miApp.AppS.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO findAllUsers();

    //UserDTO authenticateUser(String email, String password);

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long userId);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    boolean deleteUser(Long userId);

}
