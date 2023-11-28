package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.dto.FarmaciaDTO;
import br.devinhouse.projeto2.model.Farmacia;
import br.devinhouse.projeto2.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @GetMapping
    public ResponseEntity<List<FarmaciaDTO>> listarFarmacias() {
        List<Farmacia> farmacias = farmaciaRepository.findAll();
        List<FarmaciaDTO> farmaciasDTO = convertToDTO(farmacias);

        return ResponseEntity.ok(farmaciasDTO);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<FarmaciaDTO> consultarFarmaciaPorCnpj(@PathVariable Long cnpj) {
        Optional<Farmacia> farmaciaOptional = farmaciaRepository.findById(cnpj);

        if (farmaciaOptional.isPresent()) {
            Farmacia farmacia = farmaciaOptional.get();
            FarmaciaDTO farmaciaDTO = new FarmaciaDTO(
                    farmacia.getCnpj(),
                    farmacia.getRazaoSocial(),
                    farmacia.getNomeFantasia(),
                    farmacia.getEmail(),
                    farmacia.getTelefone(),
                    farmacia.getCelular(),
                    farmacia.getEndereco()
            );

            return ResponseEntity.ok(farmaciaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private List<FarmaciaDTO> convertToDTO(List<Farmacia> farmacias) {
        List<FarmaciaDTO> farmaciasDTO = new ArrayList<>();

        for (Farmacia farmacia : farmacias) {
            farmaciasDTO.add(new FarmaciaDTO(
                    farmacia.getCnpj(),
                    farmacia.getRazaoSocial(),
                    farmacia.getNomeFantasia(),
                    farmacia.getEmail(),
                    farmacia.getTelefone(),
                    farmacia.getCelular(),
                    farmacia.getEndereco()
            ));
        }

        return farmaciasDTO;
    }
}

