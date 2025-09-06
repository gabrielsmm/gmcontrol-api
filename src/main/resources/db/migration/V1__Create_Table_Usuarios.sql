DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'usuario_status_enum') THEN
        CREATE TYPE usuario_status_enum AS ENUM ('ativo', 'inativo');
    END IF;
END$$;

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nome_usuario VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    status usuario_status_enum NOT NULL DEFAULT 'ativo'
);

INSERT INTO usuarios (id, nome, nome_usuario, email, senha, status) VALUES (1, 'Gabriel Mendes', 'gabrielsmm', 'gabrielsmm251@gmail.com', '$2a$10$0Y7oV.wzcvyFa1y98KnvAO4caywHSUzJUgsBWod3E4Ghc8Uqx1aoe', 'ativo') ON CONFLICT DO NOTHING;