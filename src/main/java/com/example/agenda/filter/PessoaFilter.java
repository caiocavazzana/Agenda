package com.example.agenda.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFilter {
    private Long id;

    private String nome;

    private String cpf;

    private String dataNascimento;
}