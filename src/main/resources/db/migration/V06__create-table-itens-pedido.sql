create table itens_pedido (
    id serial,
    produto_id int not null,
    quantidade float(10) not null,
    preco decimal(10, 2) not null,
    sub_total decimal(10, 2) not null,
    pedido_id int,

    primary key(id),
    foreign key(produto_id) references produtos(id),
    foreign key(pedido_id) references pedidos(id)
);