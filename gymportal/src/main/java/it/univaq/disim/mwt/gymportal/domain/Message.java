package it.univaq.disim.mwt.gymportal.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
public class Message {

    @Id
    private Long Id;

    @Field("date")
    //controlla tipo
    private LocalDateTime date;

    @Field("message")
    private String message;

}
