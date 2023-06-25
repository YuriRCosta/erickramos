package br.com.erickramos.model;

import br.com.erickramos.enums.MaterialCabecote;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "cabecotes")
public class Cabecote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MaterialCabecote material;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer qtdValvulas;

    public MaterialCabecote getMaterial() {
        return material;
    }

    public void setMaterial(MaterialCabecote material) {
        this.material = material;
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

    public Integer getQtdValvulas() {
        return qtdValvulas;
    }

    public void setQtdValvulas(Integer qtdValvulas) {
        this.qtdValvulas = qtdValvulas;
    }
}
