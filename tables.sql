create table things (
    id serial not null,
    user_id int not null,
    thing text not null,
    timelimit text not null,
    processed int default 0,
    primary key (id),
    foreign key (user_id) references users(id)
)

create table users (
    id serial not null,
    name varchar not null,
    primary key (id)
)
