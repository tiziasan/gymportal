package it.univaq.disim.mwt.gymportal.domain;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Set;

@Data
@Document(collection = "chats")
public class Chat {

    @Id
    private String Id;

    private Long user_id;

    private Long gym_id;

    @DBRef
    private Set<Message> messages;

    public void addMessage(Message msg){
        messages.add(msg);
    }
}
