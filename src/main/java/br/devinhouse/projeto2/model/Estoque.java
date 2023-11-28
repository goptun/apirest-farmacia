package br.devinhouse.projeto2.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ESTOQUES")
@IdClass(IdEstoque.class)
public class Estoque {
    @Id
    private Long cnpj;
    @Id
    @Column(name = "nro_registro")
    private Integer nroRegistro;
    private Integer quantidade;
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public Estoque() {
        // Construtor padrão vazio necessário para JPA
    }

    public Estoque(Long cnpj, Integer nroRegistro, Integer quantidade, LocalDateTime dataAtualizacao) {
        this.cnpj = cnpj;
        this.nroRegistro = nroRegistro;
        this.quantidade = quantidade;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(Integer nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}

