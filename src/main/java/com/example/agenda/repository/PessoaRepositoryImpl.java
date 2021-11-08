package com.example.agenda.repository;

import com.example.agenda.filter.PessoaFilter;
import com.example.agenda.model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaRepositoryImpl implements PessoaRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Pessoa>> findBy(PessoaFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteriaQuery.from(Pessoa.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getId() != null)
            predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));

        if (filter.getNome() != null && !filter.getNome().isEmpty())
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get("nome")), "%" + filter.getNome().toUpperCase() + "%"));

        if (filter.getCpf() != null && !filter.getCpf().isEmpty())
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get("cpf")), "%" + filter.getCpf().toUpperCase() + "%"));

        if (filter.getDataNascimento() != null && !filter.getDataNascimento().isEmpty())
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    root.get("dataNascimento").as(String.class), filter.getDataNascimento()));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return Optional.of(entityManager.createQuery(criteriaQuery).getResultList());
    }
}