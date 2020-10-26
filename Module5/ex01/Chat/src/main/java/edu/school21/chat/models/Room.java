package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room extends BaseModel {

    private String name;
    private User creator;
    private ArrayList<Message> messages;

    public Room() {
    }

    public Room(Long id, String name, User creator, ArrayList<Message> messages) {
        super(id);
        this.name = name;
        this.creator = creator;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Room rooms = (Room) o;
        return Objects.equals(name, rooms.name) &&
                Objects.equals(creator, rooms.creator) &&
                Objects.equals(messages, rooms.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, creator, messages);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", messages=" + messages +
                '}';
    }
}
