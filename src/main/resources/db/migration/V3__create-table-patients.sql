CREATE TABLE IF NOT EXISTS patients(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    identity_document varchar(14) not null unique,
    phone varchar(20) not null,
    urbanization varchar(100) not null,
    district varchar(100) not null,
    postal_code varchar(9)   not null,
    complement varchar(100),
    number varchar(20),
    province varchar(100) not null,
    city varchar(100) not null,

    primary key (id)
);