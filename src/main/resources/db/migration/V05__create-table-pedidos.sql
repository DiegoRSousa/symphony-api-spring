create table pedidos (
    id serial,
    total decimal(10, 2) not null,
    data timestamp not null,
    criado_em timestamp not null,
    atualizado_em timestamp,

    primary key(id)
);