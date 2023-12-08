package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.dto.FarmaciaDTO;
import br.devinhouse.projeto2.model.Farmacia;
import br.devinhouse.projeto2.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @GetMapping
    public ResponseEntity<List<FarmaciaDTO>> listarFarmacias() {
        List<Farmacia> farmacias = farmaciaRepository.findAll();
        List<FarmaciaDTO> farmaciasDTO = farmacias.stream().map(FarmaciaDTO::converterParaDTO).collect(Collectors.toList());

        return ResponseEntity.ok(farmaciasDTO);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<?> consultarFarmaciaPorCnpj(@PathVariable Long cnpj) {
        Optional<Farmacia> farmaciaOptional = farmaciaRepository.findById(cnpj);

        if (farmaciaOptional.isPresent()) {
            Farmacia farmacia = farmaciaOptional.get();
            FarmaciaDTO farmaciaDTO = FarmaciaDTO.converterParaDTO(farmacia);

            return ResponseEntity.ok(farmaciaDTO);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Nenhuma farmácia encontrada para o CNPJ informado: " + cnpj);
        }
    }
}
