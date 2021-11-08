create table pessoa (
    id bigserial not null constraint pessoa_pk primary key,
    nome varchar not null,
    cpf varchar not null,
    data_nascimento date not null
);

create table contato (
    id bigserial not null constraint contato_pk primary key,
    pessoa_id bigint constraint pessoa_id references pessoa,
    nome varchar not null,
    telefone varchar not null,
    email varchar not null
);