package br.devinhouse.projeto2.repository;

import br.devinhouse.projeto2.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<Estoque> findByCnpj(Long cnpj);
}
