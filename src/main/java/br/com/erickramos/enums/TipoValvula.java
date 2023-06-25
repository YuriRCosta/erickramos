package br.com.erickramos.enums;

public enum TipoValvula {

    ADMISSAO("Admissao"),
    ESCAPE("Escape");

    private String descricao;

    TipoValvula(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
