CREATE TABLE IF NOT EXISTS usuarios_modulos (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    modulo INTEGER NOT NULL,
    chave_agrupamento UUID NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

INSERT INTO usuarios_modulos (id, usuario_id, modulo, chave_agrupamento) VALUES (1, 1, 1, gen_random_uuid()) ON CONFLICT DO NOTHING;