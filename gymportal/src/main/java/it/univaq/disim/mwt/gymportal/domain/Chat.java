package it.univaq.disim.mwt.gymportal.domain;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "chat")
public class Chat {

    @Id
    private String id;

    private Long userId;

    private String userName;

    private Long gymId;

    private String gymName;

}
