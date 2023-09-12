SET client_encoding = 'UTF8';
-- SET statement_timeout = 0;
-- SET lock_timeout = 0;
-- SET idle_in_transaction_session_timeout = 0;
-- SET standard_conforming_strings = on;
-- SELECT pg_catalog.set_config('search_path', '', false);
-- SET check_function_bodies = false;
-- SET xmloption = content;
-- SET client_min_messages = warning;
-- SET row_security = off;

CREATE TABLE IF NOT EXISTS PUBLIC.pessoas(
    "id" uuid not null primary key,
    "nome" varchar(100) not null,
    "apelido" varchar(32) unique not null,
    "nascimento" varchar(12) not null,
    "stack" varchar(255),
    "search" varchar(500) GENERATED ALWAYS AS (LOWER(nome) || LOWER(apelido) || LOWER(COALESCE(stack, ''))) STORED
);

CREATE EXTENSION IF NOT EXISTS pg_trgm SCHEMA pg_catalog;

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_consulta_pessoas_trgm ON PUBLIC.pessoas USING gin("search" gin_trgm_ops);
