package br.com.agropalma.agroquart.service;

import br.com.agropalma.agroquart.domain.Hospedaria;
import br.com.agropalma.agroquart.domain.repository.HospedariaRepository;
import br.com.agropalma.agroquart.web.form.HospedariaForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h1>HospedariaService.java</h1>
 * Classe de serviço da hospedaria (contém regras de negócios).
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Service
public class HospedariaService {

    @Autowired
    private HospedariaRepository hospedariaRepository;

    @Transactional(readOnly = true)
    public Hospedaria buscarPorId(Long id) {
        return hospedariaRepository.buscarPorId(id);
    }

    @Transactional(readOnly = true)
    public List<Hospedaria> buscarTodos() {
        return hospedariaRepository.buscarTodos();
    }

    @Transactional
    public void novaHospedaria(HospedariaForm hospedariaForm) {
        Hospedaria hospedaria = new Hospedaria();
        hospedaria.setNomeHospedaria(hospedariaForm.getNomeHospedaria());

        hospedariaRepository.salvarOuAtualizar(hospedaria);
    }

    @Transactional
    public void atualizarHospedaria(HospedariaForm hospedariaForm) {
        Hospedaria hospedaria = new Hospedaria();
        hospedaria.setId(hospedariaForm.getId());
        hospedaria.setNomeHospedaria(hospedariaForm.getNomeHospedaria());

        hospedariaRepository.salvarOuAtualizar(hospedaria);
    }

    @Transactional
    public int excluir(Long id) {
        return hospedariaRepository.excluir(id);
    }
}
