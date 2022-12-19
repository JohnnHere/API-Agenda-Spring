package com.example.demo.service;

import com.example.demo.model.Contato;
import com.example.demo.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Optional<Contato> cadastrarContato(Contato contato) {

        List<Contato> allContato = contatoRepository.findAll();

        for (Contato contatoChecagem : allContato) {
            if (contatoChecagem.getCpf().equals(contato.getCpf())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contato já cadastrado.");
            }
        }

        return Optional.of(contatoRepository.save(contato));
    }

    public Optional<Contato> atualizarContato(Contato contato) {

        List<Contato> allContato = contatoRepository.findAll();

        for (Contato contatoChecagem : allContato) {
            if (contatoChecagem.getCpf().equals(contato.getCpf())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contato já cadastrado.");
            }
        }

        return Optional.of(contatoRepository.save(contato));
    }

    public Optional<Contato> deletarContato(@PathVariable Long id) {

        Optional<Contato> contato = contatoRepository.findById(id);

        if (contato.isPresent()) {
            contatoRepository.deleteById(id);
            return contato;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado.");
        }
    }
}
