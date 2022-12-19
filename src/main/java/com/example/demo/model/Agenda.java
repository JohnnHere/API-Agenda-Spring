package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 2, max = 100, message = "O campo nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O campo descrição é obrigatório")
    @Size(min = 2, max = 500, message = "O campo descrição deve ter entre 2 e 500 caracteres")
    private String descricao;

    @OneToMany
    @JsonIgnoreProperties("agenda")
    private List<Contato> contato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
