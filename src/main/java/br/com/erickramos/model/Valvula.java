package br.com.erickramos.model;

import br.com.erickramos.enums.TipoValvula;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "valvulas")
public class Valvula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoValvula tipo;

    @Column(nullable = false)
    private Double preco;

    private String codigo;

    private Integer qtdEstoque;

    @OneToMany
    private List<Cabecote> cabecote;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public TipoValvula getTipo() {
        return tipo;
    }

    public void setTipo(TipoValvula tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Cabecote> getCabecote() {
        return cabecote;
    }

    public void setCabecote(List<Cabecote> cabecote) {
        this.cabecote = cabecote;
    }
}
