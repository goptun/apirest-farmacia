package br.devinhouse.projeto2.dto;

import br.devinhouse.projeto2.model.Farmacia;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FarmaciaDTO {

    @JsonProperty("cnpj")
    private Long cnpj;

    @JsonProperty("razaoSocial")
    private String razaoSocial;

    @JsonProperty("nomeFantasia")
    private String nomeFantasia;

    @JsonProperty("email")
    private String email;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("celular")
    private String celular;

    @JsonProperty("endereco")
    private EnderecoDTO endereco;

    public FarmaciaDTO() {}

    public FarmaciaDTO(Long cnpj, String razaoSocial, String nomeFantasia, String email, String telefone,
                       String celular, EnderecoDTO endereco) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.endereco = endereco;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public static FarmaciaDTO converterParaDTO(Farmacia farmacia) {
        return new FarmaciaDTO(
                farmacia.getCnpj(),
                farmacia.getRazaoSocial(),
                farmacia.getNomeFantasia(),
                farmacia.getEmail(),
                farmacia.getTelefone(),
                farmacia.getCelular(),
                EnderecoDTO.converterParaDTO(farmacia.getEndereco())
        );
    }

    public static List<FarmaciaDTO> converterListaParaDTO(List<Farmacia> farmacias) {
        return farmacias.stream().map(FarmaciaDTO::converterParaDTO).toList();
    }
}
