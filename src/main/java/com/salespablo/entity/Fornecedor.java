package com.salespablo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String endereco;
    private String numero;
    private String bairro;
    private String email;

    @ManyToOne // cada cidade pode ter apenas um estado
    private Cidade cidade;

}
