package com.example.agenda.model.embedded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Embeddable
public class Cpf {
    @Getter
    @Column(name = "cpf")
    private String cpf;

    public Cpf(String cpf) {
        if (cpf == null)
            throw new IllegalArgumentException("Pessoa: O campo CPF é obrigatório.");

        Pattern pattern = Pattern.compile("^[0-9]*$");
        if (!pattern.matcher(cpf).matches())
            throw new IllegalArgumentException("Pessoa: Informe apenas os números do CPF.");

        String[] cpfArray = cpf.split("(?!^)");

        if (cpfArray.length != 11)
            throw new IllegalArgumentException("Pessoa: CPF inválido.");

        int val1 = 0;
        for (int i = 11; i > 2; i--) {
            val1 += Integer.parseInt(cpfArray[cpfArray.length - i]) * (i - 1);
        }

        int res1 = (val1 * 10) % 11;
        res1 = res1 == 10 ? 0 : res1;

        if (res1 != Integer.parseInt(cpfArray[9]))
            throw new IllegalArgumentException("Pessoa: CPF inválido.");

        int val2 = 0;
        for (int i = 11; i > 1; i--) {
            val2 += Integer.parseInt(cpfArray[cpfArray.length - i]) * (i);
        }

        int res2 = (val2 * 10) % 11;
        res2 = res2 == 10 ? 0 : res2;

        if (res2 != Integer.parseInt(cpfArray[10]))
            throw new IllegalArgumentException("Pessoa: CPF inválido.");

        this.cpf = cpf;
    }
}