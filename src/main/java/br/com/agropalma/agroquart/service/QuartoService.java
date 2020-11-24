package br.com.agropalma.agroquart.service;

import br.com.agropalma.agroquart.domain.Casa;
import br.com.agropalma.agroquart.domain.Quarto;
import br.com.agropalma.agroquart.domain.repository.QuartoRepository;
import br.com.agropalma.agroquart.web.form.QuartoForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h1>QuartoService.java</h1>
 * Classe de serviço do quarto (contém regras de negócios).
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private CasaService casaService;

    @Transactional(readOnly = true)
    public Quarto buscarPorId(Long id) {
        return quartoRepository.buscarPorId(id);
    }

    @Transactional(readOnly = true)
    public List<Quarto> buscarTodos() {
        return quartoRepository.buscarTodos();
    }

    @Transactional(readOnly = true)
    public List<Quarto> buscarQuartosPorCasa(Long id) {
        return quartoRepository.buscarQuartosPorCasa(id);
    }

    @Transactional
    public void novoQuarto(QuartoForm obj) {
        Quarto quarto = new Quarto();
        quarto.setCapacidade(obj.getCapacidade());

        Casa casa = casaService.buscarPorId(obj.getCasa());
        quarto.setCasa(casa);

        quartoRepository.salvarOuAtualizar(quarto);
    }

    @Transactional
    public void atualizarQuarto(QuartoForm quartoForm) {
        Quarto quarto = buscarPorId(quartoForm.getId());
        quarto.setCapacidade(quartoForm.getCapacidade());

        quartoRepository.salvarOuAtualizar(quarto);
    }

    @Transactional
    public int excluir(Long id) {
        return quartoRepository.excluir(id);
    }
}
