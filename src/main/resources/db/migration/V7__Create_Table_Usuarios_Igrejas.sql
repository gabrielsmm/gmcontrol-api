CREATE TABLE IF NOT EXISTS usuarios_igrejas (
    usuario_id BIGINT NOT NULL,
    igreja_id BIGINT NOT NULL,
    cargo_id INT,
    PRIMARY KEY (usuario_id, igreja_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_igreja FOREIGN KEY (igreja_id) REFERENCES igrejas(id) ON DELETE CASCADE,
    CONSTRAINT fk_cargo FOREIGN KEY (cargo_id) REFERENCES cargos(id) ON DELETE SET NULL
);