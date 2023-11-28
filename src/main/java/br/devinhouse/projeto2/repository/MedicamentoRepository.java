package br.devinhouse.projeto2.repository;

import br.devinhouse.projeto2.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Optional<Medicamento> findByNroRegistro(Integer nroRegistro);
}

