insert into genre (genre_id, `genre`) values (1, 'novel');
insert into genre (genre_id, `genre`) values (2, 'fantasy');
insert into genre (genre_id, `genre`) values (3, 'detective');
insert into authors (author_id, `last_name`, `first_name`) values (1, 'Pushkin', 'Alexander');
insert into authors (author_id, `last_name`, `first_name`) values (2, 'Tolkien', 'John');
insert into authors (author_id, `last_name`, `first_name`) values (3, 'Conan Doyle', 'Arthur');
insert into books (book_id, author_id, genre_id,`title`) values (1, 1, 1, 'Captain daughter');
insert into books (book_id, author_id, genre_id,`title`) values (2, 2, 2, 'Lord of the rings');
insert into books (book_id, author_id, genre_id,`title`) values (3, 3, 3, 'Sherlock Holmes');

