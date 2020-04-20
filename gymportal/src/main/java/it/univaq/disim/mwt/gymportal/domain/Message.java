package it.univaq.disim.mwt.gymportal.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
public class Message {

    @Id
    private Long Id;

    @Field("text")
    private String text;

    @Field("date")
    private LocalDateTime date;
    
    @Field("user_id")
    private Long user_id;
    
    @Field("gym_id")
    private Long gym_id;

    /*
    @Field("chat_id")
    private Long chat_id;
    */
}
