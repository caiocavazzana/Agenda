package com.example.agenda.model;

import com.example.agenda.model.embedded.Cpf;
import com.example.agenda.model.embedded.DataNascimento;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "nome")
    private String nome;

    @Embedded
    @JsonUnwrapped
    private Cpf cpf;

    @Embedded
    @JsonUnwrapped
    private DataNascimento dataNascimento;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id")
    private List<Contato> contatos;

    public Pessoa(String nome, String cpf, LocalDate dataNascimento, List<Contato> contatos) {
        this.nome = Validate.notBlank(nome, "Pessoa: O campo NOME é obrigatório.");
        this.cpf = new Cpf(cpf);
        this.dataNascimento = new DataNascimento(dataNascimento);
        this.contatos = new ArrayList<>();
        this.contatos = Validate.notEmpty(contatos, "Pessoa: É necessário informar ao menos um contato.");
//        this.contatos = Validate.notEmpty(
//                contatos.stream().peek(c -> c.setPessoaId(id)).collect(Collectors.toList()),
//                "Pessoa: É necessário informar ao menos um contato.");
    }

    public String getCpf() {
        return cpf.getCpf();
    }

    public void setCpf(String cpf) {
        this.cpf = new Cpf(cpf);
    }

    public LocalDate getDataNascimento() {
        return dataNascimento.getDataNascimento();
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = new DataNascimento(dataNascimento);
    }
}