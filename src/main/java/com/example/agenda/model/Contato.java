package com.example.agenda.model;

import com.example.agenda.model.embedded.Email;
import com.example.agenda.model.embedded.Telefone;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "contato")
public class Contato {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "pessoa_id")
    private Long pessoaId;

    @Getter
    @Column(name = "nome")
    private String nome;

    @Embedded
    @JsonUnwrapped
    private Telefone telefone;

    @Embedded
    @JsonUnwrapped
    private Email email;

    public Contato(String nome, String telefone, String email) {
        this.nome = Validate.notBlank(nome, "Contato: O campo NOME é obrigatório.");
        this.telefone = new Telefone(telefone);
        this.email = new Email(email);
    }

    public String getTelefone() {
        return telefone.getTelefone();
    }

    public void setTelefone(String telefone) {
        this.telefone = new Telefone(telefone);
    }

    public String getEmail() {
        return email.getEmail();
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }
}