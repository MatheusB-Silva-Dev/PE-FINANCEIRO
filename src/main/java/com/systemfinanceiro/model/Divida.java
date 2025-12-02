package com.systemfinanceiro.model;

//importações necessarias para o JPA reconhecer a classe como entidade do banco
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

//a anotação @Entity indica que esta classe representa uma tabela no banco de dados
@Entity
public class Divida {
    //@Id sera a referencia a cada divida criada no banco, cria ID 1,2,3..... para cada objeto criado
    @Id
    //@GeneratedValue define com o ID será gerado automaticamente famoso auto incremento
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //atributo que armazena o id definido logo acima
    private Long id;

    //atributos que representam as colunas da tabela divida
    private String descricao;
    private double valor;
    private LocalDate dataVencimento;

    //construtor vazio necessario para o JPA conseguir instanciar a classe automaticamente
    public Divida() {}

    //construtor para criar uma nova Divida manualmente, não e obrigatorio
    //o id não entra aqui pois o banco vai gerar automaticamente
    public Divida(String descricao, double valor, LocalDate dataVencimento) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
    }

    //métodos getters e setters para acessar e modificar os atributos de forma controlada
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
