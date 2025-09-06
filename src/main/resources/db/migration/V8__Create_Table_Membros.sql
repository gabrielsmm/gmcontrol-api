DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'estado_civil_enum') THEN
        CREATE TYPE estado_civil_enum AS ENUM ('solteiro','casado','divorciado','viuvo');
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'membro_status_enum') THEN
        CREATE TYPE membro_status_enum AS ENUM ('ativo','inativo','transferido');
    END IF;
END$$;

CREATE TABLE IF NOT EXISTS membros (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(255),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    cep VARCHAR(20),
    estado_civil estado_civil_enum NOT NULL DEFAULT 'solteiro',
    data_batismo DATE,
    status membro_status_enum NOT NULL DEFAULT 'ativo',
    igreja_id BIGINT NOT NULL,
    CONSTRAINT fk_igreja FOREIGN KEY (igreja_id) REFERENCES igrejas(id) ON DELETE CASCADE
);
