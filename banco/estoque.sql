/**
* Projeto de um sistema para gestão de estoque
*@author diego maia fernandes	
*@version 1.3
*/

create database estoque;
use estoque;

-- uinique não permite valores duplicados 

create table usuarios (
id int primary key auto_increment,
usuario varchar(50) not null,
login varchar(20) not null unique,
senha varchar(250) not null,
perfil varchar(50) not null
);

describe usuarios;

/***************** CRUD ***********************/

-- CREATE (inserir 5 usuarios)

-- insert into usuarios (usuario,login,senha, perfil)
-- values ('Messi','MessiFBC',md5('12345'), 'user');

insert into usuarios (usuario,login,senha)
values ('Cristiano Ronaldo','CR7real','123@senac');

insert into usuarios (usuario,login,senha)
values ('Neymar','Neyney','123@senac');

insert into usuarios (usuario,login,senha)
values ('Jude','Jude123','123@senac');

insert into usuarios (usuario,login,senha)
values ('Halland','HallandMC','123@senac');

-- inserindo uma senha criptografada

insert into usuarios (usuario, login, senha, perfil)
values(' Robson Vaamonde' , 'vava' , md5('12345'), 'admin');

insert into usuarios (usuario, login, senha, perfil)
values(' Adm' , 'admins' , md5('12345'), 'admins');

insert into usuarios (usuario, login, senha, perfil)
values(' Adms' , 'Diego' , md5('12345'), 'admin');

insert into usuarios (usuario,login,senha, perfil)
values ('Messi','MessiFBC',md5('12345'), 'user');

insert into usuarios (usuario,login,senha, perfil)
values ('Sirlene','sisa',md5('12345'), 'user');


-- READ 1 (selecionar todos os usuarios)

select * from usuarios;
select * from usuarios where login = 'admin';

-- READ 2 (selecionar um usuario especifico por id)

select usuario, login from usuarios;

-- login (usuario e senha correspondente)

select * from usuarios where login = 'sisa' and senha = md5('12345');

-- UPDATE (alterar todos os dados de um usuario especifico)

update usuarios set usuario = 'Messi' , login = 'Messi123' , senha = md5('123@senac'), perfil = 'user' where id= 2;

-- DELETE (excluir um usuario especifico)

delete from contatos where id= 4;

-- Gerar a documentação - Modelo ER (engenharia reserva)

-- criar nova tabela
drop table fornecedor;

create table fornecedores (
	idFor int primary key auto_increment,
	razaoSocial varchar(50) not null,
	fantasia varchar(50) not null,
	cnpj varchar(20) unique,
	ie varchar(20) unique,
	cep varchar(10) not null,
	endereco varchar(50) not null,
	numero varchar(6) not null,
	complemento varchar(20),
	bairro varchar(50) not null,
	cidade varchar(50) not null,
	uf char(2) not null,
	nomeContato varchar(30) not null,
	fone varchar(15) not null,
	zap varchar(15),
	email varchar(50) not null,
	site varchar(50),
	obs varchar(250)
);


insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, zap, email, site, obs) values ('Kalunga', 'Kalunga', '43.283.811/0099-76', '206292929110', '70711-000', 'Rua dos Kalungas', '1000', '', 'Distrito Asa Norte', 'Brasilia', 'DF', 'Luiz Carlos', '1234-5678', '1234-0000', 'luiz.carlos@kalunga.com.br', 'www.kalunga.com.br', 'Pode pá');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, zap, email, site, obs) values ('Kalango', 'Kalango', '44.283.811/0089-76', '207293029110', '70712-000', 'Rua dos Calangos', '2000', '', 'Calango do Norte', 'Amapá', 'AP', 'João Pedro', '1111-0000', '2222-0000', 'joao.pedro@kalango.com.br', 'www.kalango.com.br', 'Calanguinho');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, zap, email, site, obs) values ('Kabum', 'Kabum', '45.283.811/0059-76', '207293329111', '70713-000', 'Rua do Kabum', '3000', '', 'Cambuquira', 'Londrina', 'PR', 'Maria do Carmo', '3333-0000', '4444-0000', 'maria.carmo@kabum.com.br', 'www.kabum.com.br', 'Explosao');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, zap, email, site, obs) values ('Furacão', 'Furacao', '46.283.811/0059-76', '206292929112', '80711-000', 'Rua das Explosões', '4000', '', 'Explosivo', 'Blumenau', 'SC', 'Carlos Magno', '5555-0000', '6666-0000', 'carlos.magno@furacao.com.br', 'www.furacao.com.br', 'Ventania');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, zap, email, site, obs) values ('Fusca', 'Fusca', '47.283.811/0059-76', '206292929113', '90711-000', 'Rua dos carros', '5000', '', 'Fuscão Preto', 'Rocinha', 'RJ', 'Zé Pequeno', '7777-0000', '8888-0000', 'ze.pequeno@fusca.com.br', 'www.fusca.com.br', 'Vrum');

select * from fornecedores where idFor = 15;
select * from fornecedores;


-- pesquisa avançada filtrado letras
select idFor as Id, fantasia as Fornecedor, fone, nomeContato from fornecedores where fantasia like('f%');


delete from fornecedores where idFor = '2';

create table cliente (
idFor int primary key auto_increment,
nome varchar (30) not null,
cpf varchar (50) not null,
numero varchar (30) not null,
Endereco varchar (30) not null,
complemento varchar(20),
bairro varchar(50) not null,
cidade varchar(50) not null,
zap varchar (50) not null,
cep varchar(10) not null
);

describe cliente;

drop table cliente;

-- CRUD da tabela de clientes

insert into cliente(nome, cpf, numero, Endereco, complemento, bairro, cidade, zap, cep) 
values('Diego Maia', '35905128812', '46', 'Rua Francisco Gouveia', 'perto da igreja', 'Mooca', 'São Paulo', '953336542', '13245789');

select * from cliente;

/*
Relacionamento de tabela 1-N ( Um para muitos)
Chave Estrangeira (FK) - (PK)
idFor(chave estrangeira) usar mesmo nome e tipo de dados da chave primaria (PK) tabela pai)
*/

-- timestamp default current_timestamp(obtem automaticamente a data e a hora)
-- date (tipo de dados relacionados a data)
-- decimal(10,2) (tipo de dados relacionados a numeros não inteiros)
-- decima(10,2)os com 2 casas decimais)

create table produtos(
codigo int primary key auto_increment,
barcode varchar(255) unique,
produto varchar(50) not null,
descricao varchar(255),
fabricante varchar(50) not null,
datacad timestamp default current_timestamp,
dataval date,
estoque int not null,
estoquemin int not null,
unidade char(2) not  null,
localizacao varchar(50),
custo decimal(10,2) not null,
lucro decimal(10,2),
idFor int not null,
foreign key(idFor) references fornecedores(idFor)
);

describe produtos;

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values('1111111', 'Caneta BIC azul', 'Caneta BIC cor azul, ponta fina CX 50', 'BIC',20231122,20,5,'CX','Prateleira 2','38.50',20,4);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values('22222222', 'Lapiseira', 'Lapiseira grafite com borracha, grafite 0.7', 'FaberCastell',20231122,40,10,'CX','Corredor 2','50.50',25,4);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values('3333333', 'NoteBook LG', 'NoteBook de ultima geração, linux, 8GB LG', 'Microsoft',20301122,200,50,'CX','Pratileira 7','1200.00',35,3);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values('4444444', 'Discos de CD', 'Discos de Cd, faixas musicas de POP', 'Studio Musics',20991122,500,75,'CX','Corredor 8','80,50',25,28);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values('55555555', 'Microprocessador', 'Microprocessador', 'Sony',21001122,500,100,'CX','Corredor 10','800',40,2);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values('77777777', 'Carnes Bovinas', 'diferentes tipos de cortes de Carne', 'Cortes LMTD',20221222,125,75,'CX','Geladeira 1','500',30,32);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values('88888888', 'Regua 30cm', 'Regua de acrilico de 30cm', 'FaberCastell',20251122,30,5,'Un','Pratileira 10','2.50',25,3);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values('99999999', 'Borracha', 'Borracha branca simples', 'FaberCastell',20201122,9,15,'Un','Pratileira 25','1.50',45,3);


delete from produtos where codigo = 6;
select * from produtos;

/*
Relatórios (select)
*/

-- relatorio 1 (unificar produtos com fornecedores)
-- produtos id.For(FK) fornecedores.idFor (PK)

select * from produtos inner join fornecedores
on produtos.idFor = fornecedores.idFor;

-- relatorio 2(fornecedor relaiconado ao produto)

select produtos.codigo as Código, produtos.produto,  produtos.custo as valor, 
fornecedores.fantasia as Fornecedor
from produtos inner join fornecedores on produtos.idFor = fornecedores.idFor;

-- relatorio 3(invetario de estoque)

select sum(estoque * custo) as Total from produtos;

-- relatorios 4(calcular o preço de vendas)

select codigo as código, produto,custo,
(custo + (custo * lucro)/100) as venda from produtos;

-- relatorios 5(reposição de estoque)
-- '%d/%m/%Y' (dd/mm/aaaa) '%d/%m/%y (dd/mm/yy'

select codigo as código,produto,
date_format(dataval,'%d/%m/%Y') as data_validade,
estoque,estoquemin as estoque_mínimo from produtos where estoque < estoquemin;


-- relatório 5.1 (versão impressão)
select codigo,produto,
date_format(dataval,'%d/%m/%Y'),
estoque, estoquemin
from produtos where estoque < estoquemin;
	
-- relatorio 6 (produtos vencidos)

select codigo as código, produto, localizacao as localização,
date_format(dataval,'%d/%m/%Y') as data_validade,
datediff(dataval, curdate()) as dias_vencidos
from produtos where  datediff(dataval, curdate()) < 0
