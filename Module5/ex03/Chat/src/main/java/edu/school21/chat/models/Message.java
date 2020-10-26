package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Message extends BaseModel {

    private User author;
    private Room room;
    private String text;
    private LocalDateTime date;

    public Message() {}

    public Message(Long id, User author, Room room, String text, LocalDateTime date) {
        super(id);
        this.author = author;
        this.room = room;
        this.text = text;
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Message messages = (Message) o;
        return Objects.equals(author, messages.author) &&
                Objects.equals(room, messages.room) &&
                Objects.equals(text, messages.text) &&
                Objects.equals(date, messages.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, room, text, date);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", \nauthor=" + author +
                ", \nroom=" + room +
                ", \ntext='" + text + '\'' +
                ", \ndate=" + date +
                '}';
    }
}
