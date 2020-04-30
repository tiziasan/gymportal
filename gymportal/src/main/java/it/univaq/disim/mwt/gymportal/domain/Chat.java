package it.univaq.disim.mwt.gymportal.domain;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

//una volta completata la gestione delle chat, si deve andare a modificare i controller gym e user per la modifica di una palestra o di un utente
//perche'bisogna aggiornare l'utente e la gym sia su mysql che su mongo

@Data
@Document(collection = "chats")
public class Chat {

    @Id
    private String id;

    private Long userId;

    private String userName;
    
    private Long gymId;

    private String gymName;
}
