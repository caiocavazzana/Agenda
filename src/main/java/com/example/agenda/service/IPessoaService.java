package com.example.agenda.service;

import com.example.agenda.filter.PessoaFilter;
import com.example.agenda.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPessoaService {
    Pessoa save(Pessoa pessoa);

    Pessoa update(Pessoa pessoa);

    void deleteById(Long id);

    Optional<Pessoa> findById(Long id);

    Page<Pessoa> findBy(PessoaFilter filter, Pageable pageable);
}