package com.vms.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vms.demo.dto.UserDTO;
import com.vms.demo.dto.chat.ChatFullDTO;
import com.vms.demo.service.ChatService;
import com.vms.demo.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @GetMapping("{userID}/chat")
    public ChatFullDTO retrieve(@PathVariable Long userID) {
        return chatService.getChatByUserID(userID);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
