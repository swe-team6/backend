package com.vms.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vms.demo.dto.chat.ChatDTO;
import com.vms.demo.dto.chat.ChatFullDTO;
import com.vms.demo.dto.chat.MessageDTO;
import com.vms.demo.service.ChatService;
import com.vms.demo.service.MessageService;

@RestController
@RequestMapping("chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<ChatDTO> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("{chatID}")
    public ChatFullDTO retrieve(@PathVariable Long chatID) {
        return chatService.getChatById(chatID);
    }

    @GetMapping("{chatID}/messages/unread")
    public List<MessageDTO> listMessagesUnread(@PathVariable Long chatID, @RequestParam Boolean requesterIsAdmin) {
        return messageService.getUnreadMessages(chatID, !requesterIsAdmin);
    }

    @GetMapping("{chatID}/messages")
    public List<MessageDTO> listMessages(@PathVariable Long chatID) {
        return messageService.getMessagesByChatID(chatID);
    }

    @PostMapping("{chatID}/messages/read")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void readMessages(@PathVariable Long chatID) {
        messageService.readMessagesByChatID(chatID);
    }
}
