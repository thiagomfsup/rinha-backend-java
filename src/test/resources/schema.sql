CREATE TABLE IF NOT EXISTS PUBLIC.pessoas(
     id uuid not null primary key,
     nome varchar(100) not null,
     apelido varchar(32) unique not null,
     nascimento varchar(12) not null,
     stack varchar(255),
     search text GENERATED ALWAYS AS (LOWER(nome) || LOWER(apelido) || LOWER(COALESCE(stack,'')))
);
