create table authors (
    id bigserial,
    first_name varchar(255),
    last_name varchar(255),
    primary key (id)
);

create table genre (
    id bigserial,
    genre varchar(255),
    primary key (id)
);

create table books (
    id bigserial,
    title varchar(255),
    author_id bigint references authors (id) on delete cascade,
    genre_id bigint references genre (id) on delete cascade,
    primary key (id)
);

create table comments (
    id bigserial,
    text varchar(1000),
    book_id bigint references books (id) on delete cascade,
    primary key (id)
);