CREATE TABLE IF NOT EXISTS perfis (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT
);

INSERT INTO perfis (id, nome, descricao) VALUES
(1, 'MASTER', 'Superusuário, acesso total a todas as unidades e configurações globais'),
(2, 'ADMIN', 'Administrador de uma ou mais igrejas, gerencia membros, finanças e relatórios'),
(3, 'TESOUREIRO', 'Lança entradas e saídas, consulta relatórios financeiros da unidade'),
(4, 'PASTOR', 'Visualiza membros, relatórios, eventos; pode aprovar lançamentos'),
(5, 'SECRETARIO', 'Gerencia cadastros de membros, eventos e comunicados'),
(6, 'USUARIO', 'Acesso básico, somente visualização limitada') ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS usuarios_perfis (
    usuario_id BIGINT NOT NULL,
    perfil_id INT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_perfil FOREIGN KEY (perfil_id) REFERENCES perfis (id) ON DELETE CASCADE
);

INSERT INTO usuarios_perfis (usuario_id, perfil_id) VALUES (1, 1) ON CONFLICT DO NOTHING;