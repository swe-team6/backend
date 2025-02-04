package com.vms.demo.dto.chat;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vms.demo.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatFullDTO {
    private Long chatID;
    /**
     * foreign key to a non-admin user the chat is related to.
     */
    private UserDTO user;
    /**
     * last time the chat was updated, i.e. a message was sent to the chat.
     */
    private List<MessageDTO> messages;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime lastUpdated;
}
