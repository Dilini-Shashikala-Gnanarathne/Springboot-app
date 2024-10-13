package com.example.demo.controller;

import com.example.demo.dto.Response;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public Response<?> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Response<?> getUserById(@PathVariable int id){
        UserDTO userDTO = UserDTO.builder().id(id).build();
        return userService.getUser(userDTO);
    }
    @PutMapping("/{id}")
    public Response<?> update(@PathVariable int id,@RequestBody UserDTO userDTO){
        userDTO.setId(id);
        return userService.updateUser(userDTO);
    }
    @DeleteMapping("/{id}")
    public Response<?> delete(@PathVariable int id){
        UserDTO userDTO = UserDTO.builder().id(id).build();
        return userService.deleteUser(userDTO);
    }
}
