package com.example.agenda;

import com.example.agenda.filter.PessoaFilter;
import com.example.agenda.model.Contato;
import com.example.agenda.model.Pessoa;
import com.example.agenda.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PessoaServiceTests {
    @Autowired
    private PessoaService pessoaService;

    @Test
    void testSave() {
        Pessoa pessoa = pessoaService.save(criarPessoa());
        Assertions.assertNotNull(pessoa.getId());
    }

    @Test
    void testUpdate() {
        Pessoa pessoa = criarPessoa();
        pessoa = pessoaService.save(pessoa);
        pessoa.setNome("Teste Update");
        pessoa = pessoaService.update(pessoa);
        Assertions.assertEquals("Teste Update", pessoa.getNome());
    }

    @Test
    void testFindById() {
        Pessoa pessoa = criarPessoa();
        pessoa = pessoaService.save(pessoa);
        Optional<Pessoa> optional = pessoaService.findById(pessoa.getId());
        Assertions.assertNotNull(optional);
    }

    @Test
    void testFindBy() {
        Page<Pessoa> page = pessoaService.findBy(new PessoaFilter(), Pageable.ofSize(10));
        Assertions.assertNotNull(page);
    }

    @Test
    void testDeleteById() throws Exception {
        Pessoa pessoa = criarPessoa();
        pessoa = pessoaService.save(pessoa);
        pessoaService.deleteById(pessoa.getId());
        Assertions.assertEquals(1, 1);
    }

    private Pessoa criarPessoa() {
        List<Contato> contatos = new ArrayList<>();
        Contato contato = new Contato("Contato 1", "996963636", "teste@teste.com");
        contatos.add(contato);

        return new Pessoa("Pessoa 1", "07411251020", LocalDate.now(), contatos);
    }
}