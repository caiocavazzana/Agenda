package com.example.agenda.model.embedded;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Embeddable
public class DataNascimento {

    @Getter
    @Column(name = "data_nascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    public DataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null)
            throw new IllegalArgumentException("Pessoa: O campo DATA DE NASCIMENTO é obrigatório.");

        if (dataNascimento.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Pessoa: O campo DATA DE NASCIMENTO não pode ter um valor maior que a data atual.");

        this.dataNascimento = dataNascimento;
    }
}