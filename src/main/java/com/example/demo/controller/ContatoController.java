package com.example.demo.controller;


import com.example.demo.model.Contato;
import com.example.demo.repository.ContatoRepository;
import com.example.demo.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contato")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public ResponseEntity<List<Contato>> getAll() {
        return ResponseEntity.ok(contatoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> getById(@PathVariable Long id) {
        return contatoRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<Contato>> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(contatoRepository.findAllByCpfContainingIgnoreCase(cpf));
    }

    @PostMapping
    public ResponseEntity<Contato> post(@Valid @RequestBody Contato contato) {

        return contatoService.cadastrarContato(contato)
                .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

    @PutMapping
    public ResponseEntity<Contato> put(@Valid @RequestBody Contato contato) {
        return contatoService.atualizarContato(contato)
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contatoRepository.deleteById(id);
    }
}
