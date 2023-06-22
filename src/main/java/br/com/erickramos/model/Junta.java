package br.com.erickramos.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "juntas")
public class Junta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cabecotes;

    @Column(nullable = false)
    private Double preco;

    private Integer qtdEstoque = 1;

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCabecotes() {
        return cabecotes;
    }

    public void setCabecotes(String cabecotes) {
        this.cabecotes = cabecotes;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
