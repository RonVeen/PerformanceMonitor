create table athlete
(
    id      serial primary key,
    uid     varchar,
    name    varchar,
    email   varchar,
    status  varchar,
    created timestamp,
    updated timestamp
);

create index athlete__uid
    on athlete (uid);
