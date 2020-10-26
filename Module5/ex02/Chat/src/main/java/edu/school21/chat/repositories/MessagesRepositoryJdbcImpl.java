package edu.school21.chat.repositories;

import edu.school21.chat.models.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private DataSource ds;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void save(Message message) {
        if (!checkId(message.getAuthor()) || !checkId(message.getRoom())) {
            throw new NotSavedSubEntityException("This Id does not exists in DB");
        }
        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(MsgStatSQL.INSERT_STAT.QUERY);
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getRoom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, Timestamp.valueOf(message.getDate()));
            statement.executeUpdate();
            statement = connection.prepareStatement("SELECT * FROM messages WHERE date = (?)");
            statement.setTimestamp(1, Timestamp.valueOf(message.getDate()));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                message.setId(resultSet.getLong("message_id"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        Message msg = new Message();

        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(MsgStatSQL.SELECT_STAT.QUERY);
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

    private boolean checkId(BaseModel model) {
        if (model.getId() == null) {
            return false;
        }
        try {
            Connection connection = ds.getConnection();

            String stat = "";
            String table = "";

            if (model instanceof User) {
                stat = "user_id";
                table = "users";
            } else if (model instanceof Room) {
                stat = "room_id";
                table = "rooms";
            } else if (model instanceof Message) {
                stat = "message_id";
                table = "messages";
            }

            PreparedStatement statement = connection.prepareStatement("SELECT " + stat + " FROM " + table + ";");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (model.getId().equals(resultSet.getLong(stat))) {
                    return true;
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return false;
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
