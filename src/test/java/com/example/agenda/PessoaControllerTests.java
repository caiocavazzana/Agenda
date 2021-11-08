package com.example.agenda;

import com.example.agenda.model.Contato;
import com.example.agenda.model.Pessoa;
import com.example.agenda.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PessoaService pessoaService;

    @Test
    void testSave() throws Exception {
        Pessoa pessoa = criarPessoa();

        mockMvc.perform(post("/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Pessoa pessoa = criarPessoa();
        pessoa = pessoaService.save(pessoa);

        mockMvc.perform(get("/pessoa/" + pessoa.getId())
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        Pessoa pessoa = criarPessoa();
        pessoa = pessoaService.save(pessoa);

        mockMvc.perform(delete("/pessoa/" + pessoa.getId())
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    private Pessoa criarPessoa() {
        List<Contato> contatos = new ArrayList<>();
        Contato contato = new Contato("Contato 1", "996963636", "teste@teste.com");
        contatos.add(contato);

        return new Pessoa("Pessoa 1", "07411251020", LocalDate.now(), contatos);
    }
}