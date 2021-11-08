package com.example.agenda.controller;

import com.example.agenda.filter.PessoaFilter;
import com.example.agenda.model.Pessoa;
import com.example.agenda.service.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private IPessoaService iPessoaService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Pessoa pessoa) {
        ResponseEntity<?> responseEntity;

        try {
            responseEntity = ResponseEntity.ok(iPessoaService.save(pessoa));
        } catch (IllegalArgumentException e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }

        return responseEntity;
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Pessoa pessoa) {
        ResponseEntity<?> responseEntity;

        try {
            responseEntity = ResponseEntity.ok(iPessoaService.update(pessoa));
        } catch (IllegalArgumentException e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }

        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        ResponseEntity<?> responseEntity;

        try {
            iPessoaService.deleteById(id);

            responseEntity = ResponseEntity.ok("Registro excluído com sucesso.");
        } catch (EmptyResultDataAccessException ex) {
            responseEntity = ResponseEntity.notFound().build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }

        return responseEntity;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ResponseEntity<?> responseEntity;

        try {
            Optional<Pessoa> optional = iPessoaService.findById(id);

            responseEntity = optional
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().body("Registro não encontrado."));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }

        return responseEntity;
    }

    @GetMapping(value = "/findBy")
    public Page<Pessoa> findBy(PessoaFilter filter, Pageable pageable) {
        return iPessoaService.findBy(filter, pageable);
    }
}