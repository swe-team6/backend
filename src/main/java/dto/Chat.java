package dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Chat {
    private Long chatID;
    /**
     * foreign key to a non-admin user the chat is related to.
     */
    // userID
    /**
     * last time the chat was updated, i.e. a message was sent to the chat.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime lastUpdated;
}
