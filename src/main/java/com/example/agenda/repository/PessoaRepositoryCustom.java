package com.example.agenda.repository;

import com.example.agenda.filter.PessoaFilter;
import com.example.agenda.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaRepositoryCustom {
    Optional<List<Pessoa>> findBy(PessoaFilter filter);
}