package br.devinhouse.projeto2.service;

import br.devinhouse.projeto2.model.Farmacia;
import br.devinhouse.projeto2.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    public Farmacia cadastrarFarmacia(Farmacia farmacia) {

        validarCnpjUnico(farmacia.getCnpj());

        return farmaciaRepository.save(farmacia);
    }

    private void validarCnpjUnico(Long cnpj) {
        Optional<Farmacia> farmaciaExistente = farmaciaRepository.findByCnpj(cnpj);
        if (farmaciaExistente.isPresent()) {
            throw new IllegalArgumentException("CNPJ já cadastrado em outra farmácia.");
        }
    }
}

