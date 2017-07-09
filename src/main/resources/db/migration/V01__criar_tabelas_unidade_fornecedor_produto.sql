CREATE TABLE unidade (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE fornecedor (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NULL,
    cpf VARCHAR(30) NULL,
    rg VARCHAR(30) NULL,
    orgao_rg VARCHAR(30) NULL,
    cnpj VARCHAR(30) NULL,
    tipo CHAR(1) NULL,
    insc_municipal VARCHAR(18) NULL,
    insc_estadual VARCHAR(18) NULL,
    cidade VARCHAR(30) NULL,
    endereco VARCHAR(100) NULL,
    bairro VARCHAR(50) NULL,
    uf CHAR(2) NULL,
    cep VARCHAR(9) NULL,
    email VARCHAR(100) NULL,
    fone VARCHAR(16) NULL,
    celular VARCHAR(16) NULL,
    fax VARCHAR(16) NULL,
    contato VARCHAR(50) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE produto (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    codigo_unidade BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_unidade) REFERENCES unidade(codigo),
    codigo_fornecedor BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_fornecedor) REFERENCES fornecedor(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



