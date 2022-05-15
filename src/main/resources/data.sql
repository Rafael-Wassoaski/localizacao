create table cidade(
    id int not null auto_increment primary key,
    nome varchar(50) not null,
    habitantes bigint
);

insert into cidade (id, nome, habitantes) 
values (1, 'São Paulo', 123456789), 
(2, 'Canoinhas', 70000),
(3, '123', 11),
(4, '345', 11),
(5, '567', 432),
(6, '78', 12312);