package it.univaq.disim.mwt.gymportal.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    private String text;

    private String sender;

    private LocalDateTime date;

    private boolean isGym;

}
