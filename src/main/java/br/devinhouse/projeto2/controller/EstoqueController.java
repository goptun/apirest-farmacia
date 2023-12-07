package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.model.Estoque;
import br.devinhouse.projeto2.repository.FarmaciaRepository;
import br.devinhouse.projeto2.repository.MedicamentoRepository;
import br.devinhouse.projeto2.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @PostMapping
    public ResponseEntity<Object> adquirirMedicamento(@RequestBody EstoqueRequest request) {
        try {
            if (verificarExistenciaFarmacia(request.getCnpj()) || verificarExistenciaMedicamento(request.getNroRegistro())) {
                return new ResponseEntity<>("CNPJ ou número de registro não encontrados", HttpStatus.BAD_REQUEST);
            }

            Optional<Estoque> estoqueAtualizado = estoqueService.adquirirMedicamento(
                    request.getCnpj(), request.getNroRegistro(), request.getQuantidade());

            return estoqueAtualizado.map(est -> new ResponseEntity<>((Object) est, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>("Operação falhou", HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> venderMedicamento(@RequestBody EstoqueRequest request) {
        try {
            if (verificarExistenciaFarmacia(request.getCnpj()) || verificarExistenciaMedicamento(request.getNroRegistro())) {
                return new ResponseEntity<>("CNPJ ou número de registro não encontrados", HttpStatus.BAD_REQUEST);
            }

            Optional<Estoque> estoqueAtualizado = estoqueService.venderMedicamento(
                    request.getCnpj(), request.getNroRegistro(), request.getQuantidade());

            return estoqueAtualizado.map(est -> new ResponseEntity<>((Object) est, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>("Operação falhou", HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Object> trocarMedicamento(@RequestBody TrocaMedicamentoRequest request) {
        try {
            if (verificarExistenciaFarmacia(request.getCnpjOrigem()) ||
                    verificarExistenciaFarmacia(request.getCnpjDestino()) ||
                    verificarExistenciaMedicamento(request.getNroRegistro())) {
                return new ResponseEntity<>("CNPJ ou número de registro não encontrados", HttpStatus.BAD_REQUEST);
            }

            Optional<TrocaMedicamentoResponse> estoquesAtualizados = estoqueService.trocarMedicamento(
                    request.getCnpjOrigem(), request.getCnpjDestino(), request.getNroRegistro(), request.getQuantidade());

            return estoquesAtualizados.map(est -> new ResponseEntity<>((Object) est, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>("Operação falhou", HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private boolean verificarExistenciaFarmacia(Long cnpj) {
        return farmaciaRepository.findByCnpj(cnpj).isEmpty();
    }

    private boolean verificarExistenciaMedicamento(Integer nroRegistro) {
        return medicamentoRepository.findByNroRegistro(nroRegistro).isEmpty();
    }

    public static class TrocaMedicamentoRequest {
        private Long cnpjOrigem;
        private Long cnpjDestino;
        private Integer nroRegistro;
        private Integer quantidade;


        public Long getCnpjOrigem() {
            return cnpjOrigem;
        }

        public void setCnpjOrigem(Long cnpjOrigem) {
            this.cnpjOrigem = cnpjOrigem;
        }

        public Long getCnpjDestino() {
            return cnpjDestino;
        }

        public void setCnpjDestino(Long cnpjDestino) {
            this.cnpjDestino = cnpjDestino;
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

    public static class TrocaMedicamentoResponse {
        private Integer nroRegistro;
        private Long cnpjOrigem;
        private Integer quantidadeOrigem;
        private Long cnpjDestino;
        private Integer quantidadeDestino;

        public TrocaMedicamentoResponse(Integer nroRegistro, Long cnpjOrigem, Integer quantidadeOrigem, Long cnpjDestino, Integer quantidadeDestino) {
            this.nroRegistro = nroRegistro;
            this.cnpjOrigem = cnpjOrigem;
            this.quantidadeOrigem = quantidadeOrigem;
            this.cnpjDestino = cnpjDestino;
            this.quantidadeDestino = quantidadeDestino;
        }


        public Integer getNroRegistro() {
            return nroRegistro;
        }

        public void setNroRegistro(Integer nroRegistro) {
            this.nroRegistro = nroRegistro;
        }

        public Long getCnpjOrigem() {
            return cnpjOrigem;
        }

        public void setCnpjOrigem(Long cnpjOrigem) {
            this.cnpjOrigem = cnpjOrigem;
        }

        public Integer getQuantidadeOrigem() {
            return quantidadeOrigem;
        }

        public void setQuantidadeOrigem(Integer quantidadeOrigem) {
            this.quantidadeOrigem = quantidadeOrigem;
        }

        public Long getCnpjDestino() {
            return cnpjDestino;
        }

        public void setCnpjDestino(Long cnpjDestino) {
            this.cnpjDestino = cnpjDestino;
        }

        public Integer getQuantidadeDestino() {
            return quantidadeDestino;
        }

        public void setQuantidadeDestino(Integer quantidadeDestino) {
            this.quantidadeDestino = quantidadeDestino;
        }
    }
}
