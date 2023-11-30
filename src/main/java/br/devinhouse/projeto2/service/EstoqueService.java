package br.devinhouse.projeto2.service;

import br.devinhouse.projeto2.controller.EstoqueController;
import br.devinhouse.projeto2.model.Estoque;
import br.devinhouse.projeto2.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Optional<Estoque> adquirirMedicamento(Long cnpj, Integer nroRegistro, Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser um número positivo maior que zero.");
        }

        Optional<Estoque> estoqueExistente = estoqueRepository.findByCnpjAndNroRegistro(cnpj, nroRegistro);

        if (estoqueExistente.isEmpty()) {
            Estoque novoEstoque = new Estoque(cnpj, nroRegistro, quantidade, LocalDateTime.now());
            return Optional.of(estoqueRepository.save(novoEstoque));
        } else {
            Estoque estoqueAtualizado = estoqueExistente.get();
            estoqueAtualizado.setQuantidade(estoqueAtualizado.getQuantidade() + quantidade);
            estoqueAtualizado.setDataAtualizacao(LocalDateTime.now());
            return Optional.of(estoqueRepository.save(estoqueAtualizado));
        }
    }

    public Optional<Estoque> venderMedicamento(Long cnpj, Integer nroRegistro, Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser um número positivo maior que zero.");
        }

        Optional<Estoque> estoqueExistente = estoqueRepository.findByCnpjAndNroRegistro(cnpj, nroRegistro);

        if (estoqueExistente.isEmpty()) {
            throw new IllegalArgumentException("Registro de estoque não encontrado para o CNPJ e Nro de Registro informados.");
        }

        Estoque estoqueAtualizado = estoqueExistente.get();

        if (quantidade > estoqueAtualizado.getQuantidade()) {
            throw new IllegalArgumentException("Quantidade vendida maior que a quantidade em estoque.");
        }

        estoqueAtualizado.setQuantidade(estoqueAtualizado.getQuantidade() - quantidade);
        estoqueAtualizado.setDataAtualizacao(LocalDateTime.now());

        if (estoqueAtualizado.getQuantidade() == 0) {
            estoqueRepository.delete(estoqueAtualizado);
        } else {
            estoqueRepository.save(estoqueAtualizado);
        }

        return Optional.of(estoqueAtualizado);
    }

    public Optional<EstoqueController.TrocaMedicamentoResponse> trocarMedicamento(
            Long cnpjOrigem, Long cnpjDestino, Integer nroRegistro, Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser um número positivo maior que zero.");
        }

        Optional<Estoque> estoqueOrigem = estoqueRepository.findByCnpjAndNroRegistro(cnpjOrigem, nroRegistro);
        Optional<Estoque> estoqueDestino = estoqueRepository.findByCnpjAndNroRegistro(cnpjDestino, nroRegistro);

        if (estoqueOrigem.isEmpty() || estoqueDestino.isEmpty()) {
            throw new IllegalArgumentException("Registro de estoque não encontrado para o CNPJ e Nro de Registro informados.");
        }

        Estoque estoqueOrigemAtualizado = estoqueOrigem.get();
        Estoque estoqueDestinoAtualizado = estoqueDestino.get();

        if (quantidade > estoqueOrigemAtualizado.getQuantidade()) {
            throw new IllegalArgumentException("Quantidade trocada maior que a quantidade em estoque na farmácia de origem.");
        }

        estoqueOrigemAtualizado.setQuantidade(estoqueOrigemAtualizado.getQuantidade() - quantidade);
        estoqueOrigemAtualizado.setDataAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoqueOrigemAtualizado);

        estoqueDestinoAtualizado.setQuantidade(estoqueDestinoAtualizado.getQuantidade() + quantidade);
        estoqueDestinoAtualizado.setDataAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoqueDestinoAtualizado);

        if (estoqueOrigemAtualizado.getQuantidade() == 0) {
            estoqueRepository.delete(estoqueOrigemAtualizado);
        }

        return Optional.of(new EstoqueController.TrocaMedicamentoResponse(
                nroRegistro,
                cnpjOrigem,
                estoqueOrigemAtualizado.getQuantidade(),
                cnpjDestino,
                estoqueDestinoAtualizado.getQuantidade()));
    }

    public List<Estoque> consultarEstoque(Long cnpj) {
        return estoqueRepository.findByCnpj(cnpj);
    }
}
