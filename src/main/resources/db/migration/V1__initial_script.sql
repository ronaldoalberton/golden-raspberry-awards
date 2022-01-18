create table studio (
    studio_id integer auto_increment not null,
    name varchar(100) not null
);

create table producer (
    producer_id integer auto_increment not null,
    name varchar(100) not null
);

create table movie (
    movie_id integer auto_increment not null,
    name varchar(100) not null,
    year integer not null,
    winner bit not null default 0
);

create table movie_studio (
    movie_studio_id integer auto_increment not null,
    movie_id integer not null,
    studio_id integer not null
);

alter table movie_studio
    add constraint fk_movie_studio_movie_id
        foreign key(movie_id) references movie(movie_id);

alter table movie_studio
    add constraint fk_movie_studio_studio_id
        foreign key(studio_id) references studio(studio_id);

create table movie_producer (
    movie_producer_id integer auto_increment not null,
    movie_id integer not null,
    producer_id integer not null
);

alter table movie_producer
    add constraint fk_movie_producer_movie_id
        foreign key(movie_id) references movie(movie_id);

alter table movie_producer
    add constraint fk_movie_producer_producer_id
        foreign key(producer_id) references producer(producer_id);
