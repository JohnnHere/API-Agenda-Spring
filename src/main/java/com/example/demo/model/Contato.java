package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "tb_contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 2, max = 100, message = "O campo nome deve ter no mínimo 2 e no máximo 100 caracteres")
    private String nome;
    @NotNull
    @Range(min = 1, max = 999999999)
    private Integer telefoneResidencial;
    @NotBlank(message = "O campo CPF é obrigatório")
    @Size(min = 1, max = 20, message = "O campo CPF deve conter até 20 caracteres")
    private String cpf;
    @Range(min = 1, max = 999999999)
    private Integer telefoneComercial;

    @ManyToOne
    @JsonIgnoreProperties("contato")
    private Agenda agenda;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    public int getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(int telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public int getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(int telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }
}
