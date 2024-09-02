create table users (
    username varchar(50) not null primary key,
    password varchar(500) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,

    constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

insert into users (username, password, enabled) values ('user', '{noop}EazyBytes@12345', 1);
insert into authorities (username, authority) values ('user', 'read');

insert into users (username, password, enabled) values ('admin', '{bcrypt}$2a$12$rcN9Y3prF2zUpRuD6w0CVOiblSUV5mGBYRS5.ExoT4crSFwNVZA7m', 1);
insert into authorities (username, authority) values ('admin', 'admin');

create table customer (
    id int primary key not null auto_increment,
    email varchar(45) not null,
    pwd varchar(200) not null,
    role varchar(45) not null
);

insert into customer (email, pwd, role) values('happy@example.com', '{noop}EazyBytes@12345', 'read');
insert into customer (email, pwd, role) values('admin@example.com',
                                               '{bcrypt}$2a$12$rcN9Y3prF2zUpRuD6w0CVOiblSUV5mGBYRS5
.ExoT4crSFwNVZA7m', 'admin');