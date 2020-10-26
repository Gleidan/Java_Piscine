package edu.school21.chat.app;

import edu.school21.chat.models.*;
import edu.school21.chat.repositories.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class Program {

    public static final String user = "jconcent";
    public static final String password = "BestPassword123";
    public static final String url = "jdbc:postgresql://localhost:5432/chat";

    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);

        MessagesRepositoryJdbcImpl msg = new MessagesRepositoryJdbcImpl(new HikariDataSource(config));

        Optional<Message> messageOptional = msg.findById(1L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setAuthor(null);
            message.setRoom(null);
            message.setText("Bye");
            message.setDate(null);
            msg.update(message);
        }
    }
}
