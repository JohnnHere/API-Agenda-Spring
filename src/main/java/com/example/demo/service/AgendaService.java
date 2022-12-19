package com.example.demo.service;

import com.example.demo.model.Agenda;
import com.example.demo.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Optional<Agenda> cadastrarAgenda(Agenda agenda) {

        List<Agenda> allAgenda = agendaRepository.findAll();

        for(Agenda agendaChecagem : allAgenda) {
            if(agendaChecagem.getNome().equals(agenda.getNome())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agenda já cadastrada.");
            }
        }

        return Optional.of(agendaRepository.save(agenda));
    }

    public Optional<Agenda> atualizarAgenda(Agenda agenda) {

        List<Agenda> allAgenda = agendaRepository.findAll();

        for(Agenda agendaChecagem : allAgenda) {
            if(agendaChecagem.getNome().equals(agenda.getNome())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agenda já cadastrada.");
            }
        }

        return Optional.of(agendaRepository.save(agenda));
    }

   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        agendaRepository.deleteById(id);

    }

}
