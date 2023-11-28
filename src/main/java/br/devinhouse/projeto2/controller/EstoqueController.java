package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.model.Estoque;
import br.devinhouse.projeto2.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping("/{cnpj}")
    public ResponseEntity<List<Estoque>> consultarEstoquePorFarmacia(@PathVariable Long cnpj) {
        List<Estoque> estoque = estoqueRepository.findByCnpj(cnpj);

        if (estoque.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(estoque, HttpStatus.OK);
        }
    }
}
