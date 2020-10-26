INSERT INTO users (user_id, login, password) VALUES (DEFAULT, 'gleidan', '1111');
INSERT INTO users (user_id, login, password) VALUES (DEFAULT, 'jconcent', '2222');
INSERT INTO users (user_id, login, password) VALUES (DEFAULT, 'vlad', '3333');
INSERT INTO users (user_id, login, password) VALUES (DEFAULT, 'glo_vlad', '4444');
INSERT INTO users (user_id, login, password) VALUES (DEFAULT, 'pomp', '5555');

INSERT INTO rooms (room_id, name, creator) VALUES (DEFAULT, 'family', 1);
INSERT INTO rooms (room_id, name, creator) VALUES (DEFAULT, 'job', 1);
INSERT INTO rooms (room_id, name, creator) VALUES (DEFAULT, 'students', 2);
INSERT INTO rooms (room_id, name, creator) VALUES (DEFAULT, 'univer', 3);
INSERT INTO rooms (room_id, name, creator) VALUES (DEFAULT, 'school21', 5);

INSERT INTO messages (message_id, author, room, text, date) VALUES (DEFAULT, 1, 1, 'Hello there', '2020-10-19 10:30');
INSERT INTO messages (message_id, author, room, text, date) VALUES (DEFAULT, 1, 2, 'YOU DIED', '2020-10-20 7:21');
INSERT INTO messages (message_id, author, room, text, date) VALUES (DEFAULT, 2, 3, 'YOU ARE NEXT', '2020-10-17 14:21');
INSERT INTO messages (message_id, author, room, text, date) VALUES (DEFAULT, 3, 4, 'YOUR SOUL IS MY', '2020-10-18 21:15');
INSERT INTO messages (message_id, author, room, text, date) VALUES (DEFAULT, 5, 5, 'FIGHT', '2020-10-16 7:40');

INSERT INTO users_rooms (user_id, room_id) VALUES (1, 1);
INSERT INTO users_rooms (user_id, room_id) VALUES (1, 2);
INSERT INTO users_rooms (user_id, room_id) VALUES (1, 3);
INSERT INTO users_rooms (user_id, room_id) VALUES (2, 3);
INSERT INTO users_rooms (user_id, room_id) VALUES (2, 1);
