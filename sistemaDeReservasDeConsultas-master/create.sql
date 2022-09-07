CREATE TABLE IF NOT EXISTS dentista(
id int auto_increment primary key,
nome varchar(255),
sobrenome varchar(255),
matriculaCadastro varchar(255));

create table if not exists paciente(
id int auto_increment primary key,
nome varchar(255),
sobrenome varchar(255),
endereco varchar(255),
rg varchar(255),
dataDeAlta date
);
