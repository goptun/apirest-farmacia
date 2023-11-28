package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.model.Estoque;
import br.devinhouse.projeto2.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<Object> adquirirMedicamento(@RequestBody EstoqueRequest request) {
        try {
            Optional<Estoque> estoqueAtualizado = estoqueService.adquirirMedicamento(
                    request.getCnpj(), request.getNroRegistro(), request.getQuantidade());

            return estoqueAtualizado.map(est -> new ResponseEntity<>((Object) est, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>("Operação falhou", HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Object> consultarEstoque(@PathVariable Long cnpj) {
        try {
            List<Estoque> estoqueFarmacia = estoqueService.consultarEstoque(cnpj);

            return new ResponseEntity<>((Object) estoqueFarmacia, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> venderMedicamento(@RequestBody EstoqueRequest request) {
        try {
            Optional<Estoque> estoqueAtualizado = estoqueService.venderMedicamento(
                    request.getCnpj(), request.getNroRegistro(), request.getQuantidade());

            return estoqueAtualizado.map(est -> new ResponseEntity<>((Object) est, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>("Operação falhou", HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Inner class para representar o corpo da requisição
    public static class EstoqueRequest {
        private Long cnpj;
        private Integer nroRegistro;
        private Integer quantidade;

        public EstoqueRequest() {
        }

        public EstoqueRequest(Long cnpj, Integer nroRegistro, Integer quantidade) {
            this.cnpj = cnpj;
            this.nroRegistro = nroRegistro;
            this.quantidade = quantidade;
        }

        public Long getCnpj() {
            return cnpj;
        }

        public void setCnpj(Long cnpj) {
            this.cnpj = cnpj;
        }

        public Integer getNroRegistro() {
            return nroRegistro;
        }

        public void setNroRegistro(Integer nroRegistro) {
            this.nroRegistro = nroRegistro;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }
    }
}
