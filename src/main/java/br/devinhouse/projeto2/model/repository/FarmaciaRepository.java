package br.devinhouse.projeto2.model.repository;

import br.devinhouse.projeto2.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
    Optional<Farmacia> findByCnpj(Long cnpj);
}

