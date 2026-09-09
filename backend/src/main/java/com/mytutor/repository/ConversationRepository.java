package com.mytutor.repository;
import com.mytutor.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface ConversationRepository extends JpaRepository<Conversation,Integer>{

}
