package com.example.agenda.model.embedded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Embeddable
public class Telefone {
    @Getter
    @Column(name = "telefone")
    private String telefone;

    public Telefone(String telefone) {
        if (telefone == null)
            throw new IllegalArgumentException("Contato: O campo TELEFONE é obrigatório.");

        Pattern pattern = Pattern.compile("^[0-9]*$");
        if (!pattern.matcher(telefone).matches())
            throw new IllegalArgumentException("Pessoa: Informe apenas os números do CPF.");

        this.telefone = telefone;
    }
}
