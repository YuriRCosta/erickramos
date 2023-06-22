package br.com.erickramos.enums;

public enum MaterialCabecote {

    ALUMINIO("Aluminio"),
    FERRO("Ferro");

    private String descricao;

    MaterialCabecote(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
