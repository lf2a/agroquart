package br.com.agropalma.agroquart.service;

import br.com.agropalma.agroquart.domain.Casa;
import br.com.agropalma.agroquart.domain.Hospedaria;
import br.com.agropalma.agroquart.domain.repository.CasaRepository;
import br.com.agropalma.agroquart.web.form.CasaForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h1>CasaService.java</h1>
 * Classe de serviço da casa (contém regras de negócios).
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Service
public class CasaService {

    @Autowired
    private CasaRepository casaRepository;

    @Autowired
    private HospedariaService hospedariaService;

    @Transactional(readOnly = true)
    public Casa buscarPorId(Long id) {
        return casaRepository.buscarPorId(id);
    }

    @Transactional(readOnly = true)
    public List<Casa> buscarTodos() {
        return casaRepository.buscarTodos();
    }

    @Transactional(readOnly = true)
    public List<Casa> buscarCasasPorHospedaria(Long id) {
        return casaRepository.buscarCasasPorHospedaria(id);
    }

    @Transactional
    public void salvarOuAtualizar(Casa obj) {
        casaRepository.salvarOuAtualizar(obj);
    }

    @Transactional
    public void atualizarCasa(CasaForm casaForm) {
        Casa casa = buscarPorId(casaForm.getId());
        casa.setId(casaForm.getId());
        casa.setNumero(casaForm.getNumero());
        casa.setTipo(casaForm.getTipo());
        casa.setSexo(casaForm.getSexo());
        casa.setTerceirizado(casaForm.isTerceirizado());

        Hospedaria hospedaria = hospedariaService.buscarPorId(casaForm.getHospedaria());
        casa.setHospedaria(hospedaria);

        casaRepository.salvarOuAtualizar(casa);
    }

    @Transactional
    public void novaCasa(CasaForm casaForm) {
        Casa casa = new Casa();
        casa.setNumero(casaForm.getNumero());
        casa.setTerceirizado(casaForm.isTerceirizado());
        casa.setTipo(casaForm.getTipo());
        casa.setSexo(casaForm.getSexo());

        Hospedaria hospedaria = hospedariaService.buscarPorId(casaForm.getHospedaria());
        casa.setHospedaria(hospedaria);

        casaRepository.salvarOuAtualizar(casa);
    }

    @Transactional
    public int excluir(Long id) {
        return casaRepository.excluir(id);
    }
}
