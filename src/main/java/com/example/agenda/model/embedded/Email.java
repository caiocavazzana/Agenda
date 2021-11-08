package com.example.agenda.model.embedded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Embeddable
public class Email {
    @Getter
    @Column(name = "email")
    private String email;

    public Email(String email) {
        if (email == null)
            throw new IllegalArgumentException("Contato: O campo E-MAIL é obrigatório.");

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if (!pattern.matcher(email).matches())
            throw new IllegalArgumentException("Contato: E-MAIL inválido.");

        this.email = email;
    }
}