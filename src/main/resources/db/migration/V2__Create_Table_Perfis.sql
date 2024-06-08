CREATE TABLE IF NOT EXISTS perfis (
    perfis INTEGER NOT NULL,
    usuario_id INTEGER NOT NULL,
    PRIMARY KEY (perfis, usuario_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

INSERT INTO perfis (perfis, usuario_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO perfis (perfis, usuario_id) VALUES (2, 1) ON CONFLICT DO NOTHING;
INSERT INTO perfis (perfis, usuario_id) VALUES (3, 1) ON CONFLICT DO NOTHING;