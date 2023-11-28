package br.devinhouse.projeto2.service;

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

    public List<Estoque> consultarEstoque(Long cnpj) {

        List<Estoque> estoqueFarmacia = estoqueRepository.findByCnpj(cnpj);

        if (estoqueFarmacia.isEmpty()) {
            throw new IllegalArgumentException("Nenhum registro de estoque encontrado para o CNPJ informado.");
        }

        return estoqueFarmacia;
    }
}
