package com.vms.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vms.demo.entity.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findByChat_ChatIDOrderBySentTimeDesc(Long chatID);

    List<MessageEntity> findByChat_ChatIDAndIsReadAndAuthorIsAdminOrderBySentTimeDesc(Long chatID, Boolean isRead,
            Boolean authorIsAdmin);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Messages msg SET msg.isRead = TRUE WHERE msg.chat_id = ?1 AND msg.authorIsAdmin = ?2", nativeQuery = true)
    Long setReadByChatID(Long chatID, Boolean authorIsAdmin);
}
