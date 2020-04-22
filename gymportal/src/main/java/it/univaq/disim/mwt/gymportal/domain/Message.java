package it.univaq.disim.mwt.gymportal.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
public class Message {

    @Id
    private Long Id;

    private String text;

    private LocalDateTime date;

    private boolean isGym;

}
