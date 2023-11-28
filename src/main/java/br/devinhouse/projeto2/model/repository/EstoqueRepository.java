package br.devinhouse.projeto2.model.repository;

import br.devinhouse.projeto2.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByCnpjAndNroRegistro(Long cnpjFarmacia, Integer nroRegistro);
}

