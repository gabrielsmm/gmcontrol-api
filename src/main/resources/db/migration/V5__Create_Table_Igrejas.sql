CREATE TABLE IF NOT EXISTS igrejas (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    cep VARCHAR(20),
    telefone VARCHAR(20),
    email VARCHAR(100),
    data_fundacao DATE,
    representante VARCHAR(255),
    site VARCHAR(255),
    observacoes TEXT
);
