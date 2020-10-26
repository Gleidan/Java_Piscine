package edu.school21.chat.repositories;

import edu.school21.chat.models.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private DataSource ds;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Message msg = new Message();

        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE message_id = (?)");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long authorId = resultSet.getLong("author");
                Long roomId = resultSet.getLong("room");
                msg.setText(resultSet.getString("text"));
                msg.setDate(resultSet.getTimestamp("date").toLocalDateTime());
                msg.setAuthor(findAuthor(authorId));
                msg.setRoom(findRoom(roomId));
                msg.setId(id);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        Optional<Message> optionalMessage = Optional.of(msg);

        return optionalMessage;
    }

    private User findAuthor(Long id) throws SQLException {
        User author = new User();

        Connection connection = ds.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_id = (?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            author.setLogin(resultSet.getString("login"));
            author.setPassword(resultSet.getString("password"));
            author.setCreatedRooms(null);
            author.setRooms(null);
            author.setId(id);
        }
        connection.close();
        return author;
    }

    private Room findRoom(Long id) throws SQLException {
        Room roomMsg = new Room();

        Connection connection = ds.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms WHERE room_id = (?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            roomMsg.setName(resultSet.getString("name"));
            roomMsg.setMessages(null);
            roomMsg.setId(id);
        }
        connection.close();
        return roomMsg;
    }
}
