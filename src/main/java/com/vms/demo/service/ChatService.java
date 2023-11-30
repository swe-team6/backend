package com.vms.demo.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vms.demo.dto.chat.ChatDTO;
import com.vms.demo.dto.chat.ChatFullDTO;
import com.vms.demo.entity.ChatEntity;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.ChatRepository;
import com.vms.demo.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<ChatDTO> getAllChats() {
        List<ChatEntity> chats = chatRepository.findAll(Sort.by(Sort.Direction.DESC, "lastUpdated"));
        return modelMapper.map(chats, new TypeToken<List<ChatDTO>>() {
        }.getType());
    }

    public ChatFullDTO getChatById(Long chatID) {
        Optional<ChatEntity> chatOptional = chatRepository.findById(chatID);
        if (!chatOptional.isPresent()) {
            throw new EntityNotFoundException("Chat not found with id: " + chatID);
        }
        ChatEntity chat = chatOptional.get();
        ChatFullDTO chatDTO = modelMapper.map(chat, ChatFullDTO.class);
        return chatDTO;
    }

    public ChatFullDTO getChatByUserID(Long userID) {
        Optional<UserEntity> userOptional = userRepository.findById(userID);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("User not found with id: " + userID);
        }
        UserEntity user = userOptional.get();
        ChatEntity chat = user.getChat();
        if (chat == null) {
            chat = new ChatEntity();
            chat.setLastUpdated(ZonedDateTime.now());
            chat.setUser(user);
            chatRepository.save(chat);
        }
        ChatFullDTO chatDTO = modelMapper.map(chat, ChatFullDTO.class);
        return chatDTO;
    }

    public void deleteChatByID(Long chatID) {
        chatRepository.deleteById(chatID);
    }
}