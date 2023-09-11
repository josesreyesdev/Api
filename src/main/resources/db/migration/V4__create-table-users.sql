CREATE TABLE IF NOT EXISTS users(
    id bigint not null auto_increment,
    user_id bigint not null,
    title varchar(100) not null,
    body varchar(500) not null,

    primary key (id)
);