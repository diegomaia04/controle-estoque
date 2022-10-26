/**
* Projeto de um sistema para gestão de estoque
*@author diego maia fernandes	
*@version 1.0
*/

create database estoque;
use estoque;

create table usuarios (
id int primary key auto_increment,
usuario varchar(50) not null,
login varchar(20) not null,
senha varchar(250) not null
);

describe usuarios;

/***************** CRUD ***********************/

-- CREATE (inserir 5 usuarios)

insert into usuarios (usuario,login,senha)
values ('Messi','MessiFBC','123@senac');

insert into usuarios (usuario,login,senha)
values ('Cristiano Ronaldo','CR7real','123@senac');

insert into usuarios (usuario,login,senha)
values ('Neymar','Neyney','123@senac');

insert into usuarios (usuario,login,senha)
values ('Jude','Jude123','123@senac');

insert into usuarios (usuario,login,senha)
values ('Halland','HallandMC','123@senac');

-- READ 1 (selecionar todos os usuarios)

select * from usuarios;

-- READ 2 (selecionar um usuario especifico por id)

select usuario, login from usuarios;

-- UPDATE (alterar todos os dados de um usuario especifico)

update usuarios set usuario = 'Jude' , login = 'Jude123' , senha = '123@senac' where id= 4;

-- DELETE (excluir um usuario especifico)

delete from contatos where id= 4;

-- Gerar a documentação - Modelo ER (engenharia reserva)

 

