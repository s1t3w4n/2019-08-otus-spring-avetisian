insert into genre (id, genre) values (1, 'novel');
insert into genre (id, genre) values (2, 'fantasy');
insert into genre (id, genre) values (3, 'detective');
insert into authors (id, last_name, first_name) values (1, 'Pushkin', 'Alexander');
insert into authors (id, last_name, first_name) values (2, 'Tolkien', 'John');
insert into authors (id, last_name, first_name) values (3, 'Conan Doyle', 'Arthur');
insert into books (id, author_id, genre_id, title) values (1, 1, 1, 'Captain`s daughter');
insert into books (id, author_id, genre_id, title) values (2, 2, 2, 'Lord of the rings');
insert into books (id, author_id, genre_id, title) values (3, 3, 3, 'Sherlock Holmes');
insert into comments (id, text, book_id) values (1, 'The best book in the world!!!', 1);
insert into comments (id, text, book_id) values (2, 'Greatest I have ever seen', 1);
insert into comments (id, text, book_id) values (3, 'Boring...', 2);
-- https://bcrypt-generator.com/
-- login == password (example: l/p - admin/admin )
insert into users (id, login, password, role) values
(1, 'admin', '$2y$06$2toScVggylkdjSm7jsfwhec/4afHqHh6ElmIkSKpzxxri9ZbrtzSe', 'ADMIN'),
(2, 'user', '$2y$06$wr/Ml.SjcoQNAv/UYtciz.zBdSHw3FTc/3WQe5JIMx4yfm4oC97XK', 'USER'),
(3, 'guest', '$2y$06$ilavE6lBH8zJhon89gMY6OFZTVL/naEP1a4eGpJ4JRiClStyrMa56', 'GUEST');

-- acl

INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'admin');

INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.hw13.models.Comment');

-- INSERT INTO system_message(id,content) VALUES
-- (1,'First Level Message'),
-- (2,'Second Level Message'),
-- (3,'Third Level Message');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 1, 0),
(2, 1, 2, NULL, 1, 0),
(3, 1, 3, NULL, 1, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 0, 1, 1, 1),
(2, 2, 2, 1, 0, 1, 1, 1),
(3, 3, 3, 1, 0, 1, 1, 1);
