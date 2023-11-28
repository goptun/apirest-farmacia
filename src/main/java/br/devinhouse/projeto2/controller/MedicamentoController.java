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

        if (medicamentoRepository.existsByNroRegistro(novoMedicamento.getNroRegistro())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nro de Registro já cadastrado.");
        }

        Medicamento medicamentoSalvo = medicamentoRepository.save(novoMedicamento);


        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoSalvo);
    }

}
