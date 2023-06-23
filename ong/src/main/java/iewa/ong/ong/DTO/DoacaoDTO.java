package iewa.ong.ong.DTO;

import java.util.Date;

public class DoacaoDTO {
    String nomeDoador, descricao;
    int id, quantidade;
    float quantia;
    Date data;

    public String getNomeDoador() {
        return this.nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getQuantia() {
        return this.quantia;
    }

    public void setQuantia(float quantia) {
        this.quantia = quantia;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
