package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.dto.FarmaciaDTO;
import br.devinhouse.projeto2.model.Farmacia;
import br.devinhouse.projeto2.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @GetMapping
    public ResponseEntity<List<FarmaciaDTO>> consultarFarmacias() {
        List<Farmacia> farmacias = farmaciaRepository.findAll();
        List<FarmaciaDTO> farmaciasDTO = FarmaciaDTO.converterListaParaDTO(farmacias);
        return ResponseEntity.status(HttpStatus.OK).body(farmaciasDTO);
    }
}

