package edu.school21.chat.repositories;

public enum MsgStatSQL {
    INSERT_STAT("INSERT " +
            "INTO " +
            "messages " +
            "(message_id, author, room, text, date) " +
            "VALUES (DEFAULT, (?), (?), (?), (?))"),
    SELECT_STAT("SELECT " +
            "* " +
            "FROM " +
            "messages " +
            "WHERE message_id = (?)"),
    UPDATE_STAT("UPDATE " +
            "messages " +
            "SET " +
            "author = (?), " +
            "room = (?), " +
            "text = (?), " +
            "date = (?) " +
            "WHERE message_id = (?);");

    String QUERY;

    MsgStatSQL(String QUERY) {
        this.QUERY = QUERY;
    }

}
