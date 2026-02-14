create table if not exists users
(
    id       serial
        primary key,
    name     varchar,
    email    varchar
        unique,
    password varchar
);

alter table users
    owner to zhenshen;

create table if not exists admin
(
    admin integer
        constraint admin_pk
            unique
        constraint admin_users_id_fk
            references users
);

alter table admin
    owner to zhenshen;

create table if not exists anime
(
    id           bigint not null
        constraint anime_pk
            primary key,
    title        varchar,
    picture_path varchar,
    synopsis     varchar,
    rank         integer,
    score        double precision
);

alter table anime
    owner to zhenshen;



create table if not exists genre
(
    id   integer not null
        constraint genre_pk
            primary key,
    name varchar
        constraint genre_pk_2
            unique
);

alter table genre
    owner to zhenshen;


create table if not exists anime_genre
(
    anime_id bigint
        constraint anime_genre_anime_id_fk
            references anime,
    genre_id integer
        constraint anime_genre_genre_id_fk
            references genre
);

alter table anime_genre
    owner to zhenshen;