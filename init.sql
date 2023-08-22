SET client_encoding = 'UTF8';

CREATE TABLE IF NOT EXISTS PUBLIC.pessoas(
    "id" uuid not null primary key,
    "nome" varchar(100) not null,
    "apelido" varchar(32) unique not null,
    "nascimento" varchar(12) not null,
    "stack" varchar(255)
);

CREATE EXTENSION IF NOT EXISTS pg_trgm SCHEMA pg_catalog;

CREATE INDEX idx_pessoas_apelido_trgm ON PUBLIC.pessoas USING gin("apelido" gin_trgm_ops);
CREATE INDEX idx_pessoas_nome_trgm ON PUBLIC.pessoas USING gin("nome" gin_trgm_ops);
CREATE INDEX idx_pessoas_stack_trgm ON PUBLIC.pessoas USING gin("stack" gin_trgm_ops);
