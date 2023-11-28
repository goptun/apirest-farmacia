package br.devinhouse.projeto2.controller;

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
            Farmacia novaFarmacia = farmaciaService.cadastrarFarmacia(farmacia);
            return new ResponseEntity<>(novaFarmacia, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
