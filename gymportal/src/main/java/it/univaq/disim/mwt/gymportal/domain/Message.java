package it.univaq.disim.mwt.gymportal.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    @NotBlank
    private String text;

    @DBRef
    private Chat chat;

    private LocalDateTime date;

    private boolean isGym;

    public Message() {
    }

    public Message(String id, String text, Chat chat, LocalDateTime date, boolean isGym) {
        this.id = id;
        this.text = text;
        this.chat = chat;
        this.date = date;
        this.isGym = isGym;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isGym() {
        return isGym;
    }

    public void setGym(boolean gym) {
        isGym = gym;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return isGym() == message.isGym() &&
                getId().equals(message.getId()) &&
                getText().equals(message.getText()) &&
                getChat().equals(message.getChat()) &&
                getDate().equals(message.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getChat().getId());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", chat=" + chat +
                ", date=" + date +
                ", isGym=" + isGym +
                '}';
    }
}
