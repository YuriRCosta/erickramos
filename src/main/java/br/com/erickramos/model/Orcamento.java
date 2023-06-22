package br.com.erickramos.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orcamentos")
public class Orcamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Cabecote cabecote;

    private Boolean plaina = Boolean.FALSE;
    private Boolean retificaComando = Boolean.FALSE;
    private Boolean retentorValvula = Boolean.FALSE;
    private Boolean retentorComando = Boolean.FALSE;

    private Integer assentamentoValvula = 0;
    private Integer valvulaEscape = 0;
    private Integer valvulaAdmissao = 0;
    private Integer selo = 0;

    private Date data = new Date();

    @OneToOne
    private Junta junta;

    private Double valor;
    private Double valorAdicional;
    private Double valorTotal = 0.0;

    private String descricao;

    private Boolean aprovado = Boolean.FALSE;

    public Integer getAssentamentoValvula() {
        return assentamentoValvula;
    }

    public void setAssentamentoValvula(Integer assentamentoValvula) {
        this.assentamentoValvula = assentamentoValvula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cabecote getCabecote() {
        return cabecote;
    }

    public void setCabecote(Cabecote cabecote) {
        this.cabecote = cabecote;
    }

    public Boolean getPlaina() {
        return plaina;
    }

    public void setPlaina(Boolean plaina) {
        this.plaina = plaina;
    }

    public Boolean getRetificaComando() {
        return retificaComando;
    }

    public void setRetificaComando(Boolean retificaComando) {
        this.retificaComando = retificaComando;
    }

    public Boolean getRetentorValvula() {
        return retentorValvula;
    }

    public void setRetentorValvula(Boolean retentorValvula) {
        this.retentorValvula = retentorValvula;
    }

    public Boolean getRetentorComando() {
        return retentorComando;
    }

    public void setRetentorComando(Boolean retentorComando) {
        this.retentorComando = retentorComando;
    }

    public Integer getValvulaEscape() {
        return valvulaEscape;
    }

    public void setValvulaEscape(Integer valvulaEscape) {
        this.valvulaEscape = valvulaEscape;
    }

    public Integer getValvulaAdmissao() {
        return valvulaAdmissao;
    }

    public void setValvulaAdmissao(Integer valvulaAdmissao) {
        this.valvulaAdmissao = valvulaAdmissao;
    }

    public Integer getSelo() {
        return selo;
    }

    public void setSelo(Integer selo) {
        this.selo = selo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Junta getJunta() {
        return junta;
    }

    public void setJunta(Junta junta) {
        this.junta = junta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(Double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }
}
