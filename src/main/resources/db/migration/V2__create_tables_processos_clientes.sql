CREATE TABLE IF NOT EXISTS processos (
    id SERIAL PRIMARY KEY,
    processos_numero VARCHAR(50) NOT NULL UNIQUE,
    nome VARCHAR(150),
    tribunal VARCHAR(100)

);

CREATE TABLE IF NOT EXISTS andamentos (
    id SERIAL PRIMARY KEY,
    data_andamentos DATE,
    prazo_final DATE,
    observacao VARCHAR(500),
    data_de_conferencia TIMESTAMP,
    responsavel_pela_conferencia VARCHAR(50),
    processos_numero VARCHAR(50) NOT NULL,
    CONSTRAINT fk_processos
        FOREIGN KEY (processos_numero)
        REFERENCES processos (processos_numero)
        ON DELETE CASCADE
);