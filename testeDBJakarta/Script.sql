CREATE DATABASE ControleDeOrdemDeServico;

create table ControleDeOrdemDeServico.cliente (
idCliente INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255)
);

CREATE TABLE ControleDeOrdemDeServico.ordem_servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT,
    data DATE,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);