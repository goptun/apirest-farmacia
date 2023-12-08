package br.devinhouse.projeto2.model;

import br.devinhouse.projeto2.model.enums.TipoMedicamento;
import jakarta.persistence.*;

@Entity
@Table(name = "MEDICAMENTOS")
public class Medicamento {
    @Id
    @Column(name = "nro_registro")
    private Integer nroRegistro;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private Float preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_medicamento")
    private TipoMedicamento tipo;

    public Medicamento() {

    }

    public Medicamento(Integer nroRegistro, String nome, String laboratorio, String dosagem, String descricao, Float preco, TipoMedicamento tipo) {
        this.nroRegistro = nroRegistro;
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.dosagem = dosagem;
        this.descricao = descricao;
        this.preco = preco;
        this.tipo = tipo;
    }

    public Integer getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(Integer nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public TipoMedicamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMedicamento tipo) {
        this.tipo = tipo;
    }
}
