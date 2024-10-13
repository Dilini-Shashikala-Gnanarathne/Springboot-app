package com.example.demo.service;

import com.example.demo.dto.Response;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public Response<?> getUsers() {
        List<User> users = userRepository.findAll();
        return Response.builder()
                .status(true)
                .data(users)
                .build();
    }

    public Response<?> getUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getId());
        if(user.isPresent()){
            return Response.builder()
                    .status(true)
                    .data(user.get())
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .data(null)
                    .message("Id not found")
                    .build();
        }
    }

    public Response<?> updateUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(userDTO.getId());
        if(existingUser.isPresent()){
            User user = existingUser.get();
            user.setName(userDTO.getName());
            userRepository.save(user);
            return Response.builder()
                    .status(true)
                    .data(null)
                    .message("User Updated Successfully")
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .data(null)
                    .message("Id not found")
                    .build();
        }
    }

    public Response<?> deleteUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getId());
        if(user.isPresent()){
            userRepository.deleteById(userDTO.getId());
            return Response.builder()
                    .status(true)
                    .data(null)
                    .message("User Deleted Successfully")
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .data(null)
                    .message("Id not found")
                    .build();
        }
    }
}
