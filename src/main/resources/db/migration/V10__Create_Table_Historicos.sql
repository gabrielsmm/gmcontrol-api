DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'historico_tipo_enum') THEN
        CREATE TYPE historico_tipo_enum AS ENUM ('ENTRADA','SAIDA');
    END IF;
END$$;

CREATE TABLE IF NOT EXISTS historicos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    tipo historico_tipo_enum NOT NULL,
    descricao TEXT
);

INSERT INTO historicos (nome, tipo, descricao) VALUES
('Dízimo', 'ENTRADA', 'Contribuição regular dos membros da igreja'),
('Oferta de Amor', 'ENTRADA', 'Doações voluntárias para fins gerais da igreja'),
('Outras Receitas', 'ENTRADA', 'Receitas diversas não classificadas como dízimo ou oferta'),
('Pagamento de Contas', 'SAIDA', 'Despesas da igreja, contas de água, luz, telefone, aluguel, etc.'),
('Salários e Honorários', 'SAIDA', 'Pagamentos de funcionários e ministros'),
('Manutenção e Reformas', 'SAIDA', 'Gastos com obras, reparos e manutenção das instalações da igreja'),
('Eventos e Atividades', 'SAIDA', 'Despesas relacionadas a eventos, congressos e atividades especiais') ON CONFLICT DO NOTHING;