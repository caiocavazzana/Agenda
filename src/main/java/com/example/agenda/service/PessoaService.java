package com.example.agenda.service;

import com.example.agenda.filter.PessoaFilter;
import com.example.agenda.model.Pessoa;
import com.example.agenda.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService implements IPessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        Pessoa nPessoa = new Pessoa(pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(), pessoa.getContatos());
        return pessoaRepository.save(nPessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return findById(pessoa.getId())
                .map(nPessoa -> {
                    nPessoa.setNome(pessoa.getNome());
                    nPessoa.setCpf(pessoa.getCpf());
                    nPessoa.setDataNascimento(pessoa.getDataNascimento());
                    nPessoa.setContatos(pessoa.getContatos().stream().peek(c -> c.setPessoaId(nPessoa.getId())).collect(Collectors.toList()));
                    return pessoaRepository.save(nPessoa);
                })
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada."));
    }

    @Override
    public void deleteById(Long id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        if (id == null)
            id = 0L;

        return pessoaRepository.findById(id);
    }

    @Override
    public Page<Pessoa> findBy(PessoaFilter filter, Pageable pageable) {
        List<Pessoa> pessoas = new ArrayList<>();

        pessoaRepository.findBy(filter).ifPresent(pessoas::addAll);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), pessoas.size());
        return new PageImpl<>(pessoas.subList(start, end), pageable, pessoas.size());
    }
}