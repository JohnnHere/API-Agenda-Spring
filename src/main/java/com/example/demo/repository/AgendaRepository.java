package com.example.demo.repository;

import com.example.demo.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    public List<Agenda> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
    public List<Agenda> deleteAgendaByNome(@Param("nome") String nome);

}

