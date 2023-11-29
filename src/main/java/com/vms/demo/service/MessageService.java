package com.vms.demo.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.demo.dto.chat.MessageCreateDTO;
import com.vms.demo.dto.chat.MessageDTO;
import com.vms.demo.entity.ChatEntity;
import com.vms.demo.entity.MessageEntity;
import com.vms.demo.repository.ChatRepository;
import com.vms.demo.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<MessageDTO> getMessagesByChatID(Long chatID) {
        List<MessageEntity> messages = messageRepository.findByChat_ChatIDOrderBySentTimeDesc(chatID);
        return modelMapper.map(messages, new TypeToken<List<MessageDTO>>() {
        }.getType());
    }

    public void readMessagesByChatID(Long chatID, Boolean authorIsAdmin) {
        messageRepository.setReadByChatID(chatID, authorIsAdmin);
    }

    public MessageDTO createMessage(MessageCreateDTO messageCreateDTO) {
        MessageEntity message = modelMapper.map(messageCreateDTO, MessageEntity.class);
        message.setMessageID(null);
        message.setIsRead(false);
        message.setSentTime(ZonedDateTime.now());
        message = messageRepository.save(message);
        ChatEntity chat = message.getChat();
        chat.setLastUpdated(ZonedDateTime.now());
        chatRepository.save(chat);
        return modelMapper.map(message, MessageDTO.class);
    }

    public List<MessageDTO> getUnreadMessages(Long chatID, Boolean authorIsAdmin) {
        List<MessageEntity> messages = messageRepository
                .findByChat_ChatIDAndIsReadAndAuthorIsAdminOrderBySentTimeDesc(chatID, false, authorIsAdmin);
        return modelMapper.map(messages, new TypeToken<List<MessageDTO>>() {
        }.getType());
    }
}