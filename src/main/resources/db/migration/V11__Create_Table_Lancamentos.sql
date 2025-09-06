CREATE TABLE IF NOT EXISTS lancamentos (
    id BIGSERIAL PRIMARY KEY,
    igreja_id BIGINT NOT NULL,
    membro_id BIGINT,
    historico_id INT NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    data DATE NOT NULL,
    descricao TEXT,
    CONSTRAINT fk_igreja FOREIGN KEY (igreja_id) REFERENCES igrejas(id) ON DELETE CASCADE,
    CONSTRAINT fk_membro FOREIGN KEY (membro_id) REFERENCES membros(id) ON DELETE SET NULL,
    CONSTRAINT fk_historico FOREIGN KEY (historico_id) REFERENCES historicos(id) ON DELETE CASCADE
);