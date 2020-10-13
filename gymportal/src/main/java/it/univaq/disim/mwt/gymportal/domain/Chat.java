package it.univaq.disim.mwt.gymportal.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Objects;

@Document(collection = "chats")
public class Chat {

    @Id
    private String id;

    private long userId;

    private String userName;

    private long gymId;

    private String gymName;

    public Chat() {
    }

    public Chat(long userId, String userName, long gymId, String gymName) {
        this.userId = userId;
        this.userName = userName;
        this.gymId = gymId;
        this.gymName = gymName;
    }

    public Chat(String id, long userId, String userName, long gymId, String gymName) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.gymId = gymId;
        this.gymName = gymName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getGymId() {
        return gymId;
    }

    public void setGymId(long gymId) {
        this.gymId = gymId;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chat)) return false;
        Chat chat = (Chat) o;
        return getUserId() == chat.getUserId() &&
                getGymId() == chat.getGymId() &&
                getId().equals(chat.getId()) &&
                getUserName().equals(chat.getUserName()) &&
                getGymName().equals(chat.getGymName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getGymId());
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", gymId=" + gymId +
                ", gymName='" + gymName + '\'' +
                '}';
    }
}
