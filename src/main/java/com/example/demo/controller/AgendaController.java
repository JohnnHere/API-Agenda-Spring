package com.example.demo.controller;

import com.example.demo.model.Agenda;
import com.example.demo.repository.AgendaRepository;
import com.example.demo.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agenda")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping
    public ResponseEntity<List<Agenda>> getAll() {
        return ResponseEntity.ok(agendaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> getById(@PathVariable Long id) {
        return agendaRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Agenda>> getByName(@PathVariable String nome) {
        return ResponseEntity.ok(agendaRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Agenda> post(@Valid @RequestBody Agenda agenda) {
        return agendaService.cadastrarAgenda(agenda)
                .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping
    public ResponseEntity<Agenda> put(@Valid @RequestBody Agenda agenda) {
        return agendaService.atualizarAgenda(agenda)
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        agendaRepository.deleteById(id);
    }

}
