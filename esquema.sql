
/*usar esses comando para criar o banco de dados e as tabelas*/
create database topburger;

use topburger;

CREATE TABLE Cliente (/*ok*/
    id INTEGER PRIMARY KEY,
    nome VARCHAR(55),
    telefone VARCHAR(55),
    endereco VARCHAR(55)  
);

CREATE TABLE produto (/*ok*/
    nome VARCHAR(55) PRIMARY KEY,
    preco DOUBLE,
    descricao VARCHAR(100),
);



CREATE TABLE Pedido (/*ok*/

    observacoes VARCHAR(200),
    valor DOUBLE,
    status VARCHAR(100),
    nome VARCHAR(100),
    compra DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fk_cliente_id INTEGER
);

ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_1
    FOREIGN KEY (fk_cliente_id)
    REFERENCES cliente (id)
    ON DELETE RESTRICT;


CREATE TABLE ItemPedido (/*ok*/
    codigo INTEGER PRIMARY KEY,
    quantidade INTEGER,
    fk_produto_nome VARCHAR(55),
);

ALTER TABLE ItemPedido ADD CONSTRAINT FK_ItemPedido_2
    FOREIGN KEY (fk_produto_nome)
    REFERENCES produto (nome)
    ON DELETE CASCADE;

