package br.devinhouse.projeto2.controller;

import br.devinhouse.projeto2.model.Endereco;
import br.devinhouse.projeto2.model.Estoque;
import br.devinhouse.projeto2.model.Farmacia;
import br.devinhouse.projeto2.model.Medicamento;
import br.devinhouse.projeto2.model.enums.TipoMedicamento;
import br.devinhouse.projeto2.repository.EstoqueRepository;
import br.devinhouse.projeto2.repository.FarmaciaRepository;
import br.devinhouse.projeto2.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/inicializacao")
public class InicializacaoController {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> realizarCargaInicial() {

        Farmacia farmacia1 = new Farmacia(
                90561736000121L, "DevMed Ltda", "Farmácia DevMed",
                "devmed@farmacia.com", "(44)4444-4444", "(44)9444-4441",
                new Endereco(88888999L, "Rua Porto Real", 67, "Westeros", "Berlim", "SC", 15.23456, 2.8678687)
        );

        Farmacia farmacia2 = new Farmacia(
                43178995000198L, "MedHouse Ltda", "Farmácia MedHouse",
                "medhouse@farmacia.com", "(55)5555-5555", "(45)95555-5555",
                new Endereco(8877799L, "Rua Madrid", 76, "Winterfell", "Estocolmo", "SC", 19.254356, 3.8987687)
        );

        farmaciaRepository.saveAll(List.of(farmacia1, farmacia2));

        Medicamento medicamento1 = new Medicamento(
                1010, "Programapan", "Matrix", "2x ao dia",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend", 111.00f,
                TipoMedicamento.COMUM
        );

        Medicamento medicamento2 = new Medicamento(
                7473, "Cafex", "Colombia Farm", "4x ao dia",
                "Pellentesque non ultricies mauris, ut lobortis elit. Cras nec cursus nibh", 51.50f, TipoMedicamento.COMUM
        );

        Medicamento medicamento3 = new Medicamento(
                2233, "Estomazol", "Acme", "1x ao dia",
                "Sed risus mauris, consectetur eget egestas vitae", 22.50f, TipoMedicamento.COMUM
        );

        Medicamento medicamento4 = new Medicamento(
                8880, "Gelox", "Ice", "2x ao dia",
                "Quisque quam orci, vulputate sit amet", 11.50f, TipoMedicamento.COMUM
        );

        Medicamento medicamento5 = new Medicamento(
                5656, "Aspirazol", "Acme", "3x ao dia",
                "Sed faucibus, libero iaculis pulvinar consequat, augue elit eleifend", 10.50f, TipoMedicamento.CONTROLADO
        );

        Medicamento medicamento6 = new Medicamento(
                4040, "Propolizol", "Bee", "5x ao dia",
                "Nunc euismod ipsum purus, sit amet finibus libero ultricies in", 10.50f, TipoMedicamento.CONTROLADO
        );

        medicamentoRepository.saveAll(List.of(medicamento1, medicamento2, medicamento3, medicamento4, medicamento5, medicamento6));

        LocalDateTime dataAtual = LocalDateTime.now();

        Estoque estoque1 = new Estoque(90561736000121L, 1010, 12, dataAtual);
        Estoque estoque2 = new Estoque(90561736000121L, 7473, 10, dataAtual);
        Estoque estoque3 = new Estoque(43178995000198L, 7473, 2, dataAtual);
        Estoque estoque4 = new Estoque(43178995000198L, 2233, 15, dataAtual);
        Estoque estoque5 = new Estoque(43178995000198L, 8880, 16, dataAtual);
        Estoque estoque6 = new Estoque(43178995000198L, 4040, 22, dataAtual);

        estoqueRepository.saveAll(List.of(estoque1, estoque2, estoque3, estoque4, estoque5, estoque6));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

