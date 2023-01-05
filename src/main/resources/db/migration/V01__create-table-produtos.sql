create table produtos (
    id serial,
    codigo varchar(4) not null,
    nome varchar(32) not null,
    preco decimal(10,2) not null,
    status varchar(20) not null,
    primary key(id)
);