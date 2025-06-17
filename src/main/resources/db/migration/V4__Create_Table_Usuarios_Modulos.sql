CREATE TABLE IF NOT EXISTS usuarios_modulos (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    modulo_id INTEGER NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_modulo FOREIGN KEY (modulo_id) REFERENCES modulos (id) ON DELETE CASCADE
);

INSERT INTO usuarios_modulos (id, usuario_id, modulo_id) VALUES (1, 1, 1) ON CONFLICT DO NOTHING;