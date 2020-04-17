package it.univaq.disim.mwt.gymportal.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
public class Message {

    @Id
    private Long Id;

    @Field("mittente")
    private Long idMittente;

    @Field("destinatario")
    private Long idDestinatario;

    @Field("message")
    private String message;

    @Field("date")
    //controlla tipo
    private LocalDateTime date;
}
