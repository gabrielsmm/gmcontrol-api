CREATE TABLE IF NOT EXISTS usuarios_igrejas (
    usuario_id BIGINT NOT NULL,
    igreja_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, igreja_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_igreja FOREIGN KEY (igreja_id) REFERENCES igrejas(id) ON DELETE CASCADE
);