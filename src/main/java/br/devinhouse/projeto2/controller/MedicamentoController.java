package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.model.Medicamento;
import br.devinhouse.projeto2.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @GetMapping
    public ResponseEntity<List<Medicamento>> listarMedicamentos() {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        return ResponseEntity.ok(medicamentos);
    }
}
