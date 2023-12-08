package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.model.Endereco;
import br.devinhouse.projeto2.model.Farmacia;
import br.devinhouse.projeto2.service.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/farmacias")
public class CadastraFarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    @PostMapping
    public ResponseEntity<?> cadastrarFarmacia(@RequestBody Farmacia farmacia) {
        try {
            validarCamposFarmacia(farmacia);

            Farmacia novaFarmacia = farmaciaService.cadastrarFarmacia(farmacia);
            return new ResponseEntity<>(novaFarmacia, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validarCamposFarmacia(Farmacia farmacia) {
        if (farmacia.getCnpj() == null || farmacia.getCnpj() <= 0 || String.valueOf(farmacia.getCnpj()).length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        if (farmacia.getRazaoSocial() == null || farmacia.getRazaoSocial().trim().isEmpty()) {
            throw new IllegalArgumentException("Razão Social é obrigatória");
        }

        if (farmacia.getNomeFantasia() == null || farmacia.getNomeFantasia().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome Fantasia é obrigatório");
        }

        if (farmacia.getEmail() == null || farmacia.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail é obrigatório");
        }

        if (farmacia.getTelefone() == null || farmacia.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatório");
        }

        if (farmacia.getCelular() == null || farmacia.getCelular().trim().isEmpty()) {
            throw new IllegalArgumentException("Celular é obrigatório");
        }

        validarEndereco(farmacia.getEndereco());
    }

    private void validarEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço é obrigatório");
        }

        if (endereco.getCep() == null || String.valueOf(endereco.getCep()).length() != 8) {
            throw new IllegalArgumentException("CEP obrigatório e com 8 dígitos.");
        }

        if (endereco.getLogradouro() == null || endereco.getLogradouro().trim().isEmpty()) {
            throw new IllegalArgumentException("Logradouro é obrigatório");
        }

        if (endereco.getNumero() == null) {
            throw new IllegalArgumentException("Número é obrigatório");
        }

        if (endereco.getBairro() == null || endereco.getBairro().trim().isEmpty()) {
            throw new IllegalArgumentException("Bairro é obrigatório");
        }

        if (endereco.getCidade() == null || endereco.getCidade().trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }

        if (endereco.getEstado() == null || endereco.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("Estado é obrigatório");
        }

        if (endereco.getLatitude() == null) {
            throw new IllegalArgumentException("Latitude é obrigatória");
        }

        if (endereco.getLongitude() == null) {
            throw new IllegalArgumentException("Longitude é obrigatória");
        }
    }
}
