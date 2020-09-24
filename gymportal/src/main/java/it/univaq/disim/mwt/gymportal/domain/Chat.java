package it.univaq.disim.mwt.gymportal.domain;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "chats")
public class Chat {

    @Id
    private String id;

    private long userId;

    private String userName;

    private long gymId;

    private String gymName;

}
