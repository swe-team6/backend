package com.vms.demo.dto.chat;

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
public class MessageCreateDTO {
    private Long chatID;
    private String text;
    private Boolean authorIsAdmin;
}
