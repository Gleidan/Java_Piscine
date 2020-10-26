package edu.school21.chat.app;

import edu.school21.chat.models.*;
import edu.school21.chat.repositories.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {

    public static final String user = "jconcent";
    public static final String password = "BestPassword123";
    public static final String url = "jdbc:postgresql://localhost:5432/chat";

    public static void main(String[] args) {

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);

            User creator = new User(3L, "user", "user", new ArrayList<>(), new ArrayList<>());
            User author = creator;
            Room room = new Room(2L, "room", creator, new ArrayList<>());
            Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());

            MessagesRepositoryJdbcImpl msg = new MessagesRepositoryJdbcImpl(new HikariDataSource(config));
            msg.save(message);
            System.out.println(message.getId());
    }
}
