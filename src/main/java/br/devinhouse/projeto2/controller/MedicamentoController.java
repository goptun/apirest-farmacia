package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.model.Medicamento;
import br.devinhouse.projeto2.repository.MedicamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @GetMapping
    public ResponseEntity<List<Medicamento>> consultarMedicamentos() {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        return ResponseEntity.ok(medicamentos);
    }

    @PostMapping
    public ResponseEntity<?> incluirMedicamento(@Valid @RequestBody Medicamento novoMedicamento) {
        try {
            validarCamposMedicamento(novoMedicamento);

            if (medicamentoRepository.existsByNroRegistro(novoMedicamento.getNroRegistro())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nro de Registro já cadastrado.");
            }

            Medicamento medicamentoSalvo = medicamentoRepository.save(novoMedicamento);

            return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private void validarCamposMedicamento(Medicamento medicamento) {
        if (medicamento.getNroRegistro() == null || medicamento.getNroRegistro() <= 0) {
            throw new IllegalArgumentException("Nro de Registro é obrigatório e deve ser maior que zero");
        }

        if (medicamento.getNome() == null || medicamento.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (medicamento.getLaboratorio() == null || medicamento.getLaboratorio().trim().isEmpty()) {
            throw new IllegalArgumentException("Laboratório é obrigatório");
        }

        if (medicamento.getDosagem() == null || medicamento.getDosagem().trim().isEmpty()) {
            throw new IllegalArgumentException("Dosagem é obrigatória");
        }

        if (medicamento.getPreco() == null || medicamento.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço é obrigatório e deve ser maior que zero");
        }

        if (medicamento.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de Medicamento é obrigatório");
        }

    }
}
