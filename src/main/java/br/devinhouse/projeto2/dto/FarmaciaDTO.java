package br.devinhouse.projeto2.dto;

import br.devinhouse.projeto2.model.Endereco;
import br.devinhouse.projeto2.model.Farmacia;

import java.util.List;

public class FarmaciaDTO {

    private Long cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String celular;
    private EnderecoDTO endereco;


    public FarmaciaDTO(Long cnpj, String razaoSocial, String nomeFantasia, String email, String telefone, String celular, EnderecoDTO endereco) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.endereco = endereco;
    }

    public FarmaciaDTO() {

    }

    public FarmaciaDTO(Long cnpj, String razaoSocial, String nomeFantasia, String email, String telefone, String celular, Endereco endereco) {
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
        FarmaciaDTO farmaciaDTO = new FarmaciaDTO();
        farmaciaDTO.setCnpj(farmacia.getCnpj());
        farmaciaDTO.setRazaoSocial(farmacia.getRazaoSocial());
        farmaciaDTO.setNomeFantasia(farmacia.getNomeFantasia());
        farmaciaDTO.setEmail(farmacia.getEmail());
        farmaciaDTO.setTelefone(farmacia.getTelefone());
        farmaciaDTO.setCelular(farmacia.getCelular());
        farmaciaDTO.setEndereco(EnderecoDTO.converterParaDTO(farmacia.getEndereco()));
        return farmaciaDTO;
    }

    public static List<FarmaciaDTO> converterListaParaDTO(List<Farmacia> farmacias) {
        return farmacias.stream().map(FarmaciaDTO::converterParaDTO).toList();
    }
}

