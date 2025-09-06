CREATE TABLE IF NOT EXISTS membros_cargos (
    membro_id BIGINT NOT NULL,
    cargo_id INT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    PRIMARY KEY (membro_id, cargo_id, data_inicio),
    CONSTRAINT fk_membro FOREIGN KEY (membro_id) REFERENCES membros(id) ON DELETE CASCADE,
    CONSTRAINT fk_cargo FOREIGN KEY (cargo_id) REFERENCES cargos(id) ON DELETE CASCADE
);