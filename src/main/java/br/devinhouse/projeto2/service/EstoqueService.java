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


        if (!farmaciaExiste(cnpj)) {
            throw new IllegalArgumentException("Farmácia com CNPJ " + cnpj + " não encontrada.");
        }


        if (!medicamentoExiste(nroRegistro)) {
            throw new IllegalArgumentException("Medicamento com Nro de Registro " + nroRegistro + " não encontrado.");
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

    private boolean farmaciaExiste(Long cnpj) {

        return true;
    }

    private boolean medicamentoExiste(Integer nroRegistro) {
        return true;
    }

    public List<Estoque> consultarEstoque(Long cnpj) {
        return null;
    }
}
