package br.com.agropalma.agroquart.service;

import br.com.agropalma.agroquart.domain.Permissao;
import br.com.agropalma.agroquart.domain.repository.PermissaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h1>PermissaoService.java</h1>
 * Classe de serviço da permissao (contém regras de negócios).
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22/11/2020
 */
@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarPorId(Long id) {
        return permissaoRepository.buscarPorId(id);
    }

    @Transactional(readOnly = true)
    public List<Permissao> buscarPermissoesPorIds(List<Long> ids) {
        return permissaoRepository.buscarPermissoesPorIds(ids);
    }

    @Transactional(readOnly = true)
    public List<Permissao> buscarTodos() {
        return permissaoRepository.buscarTodos();
    }

    @Transactional
    public void salvarOuAtualizar(Permissao obj) {
        permissaoRepository.salvarOuAtualizar(obj);
    }

    @Transactional
    public int excluir(Long id) {
        return permissaoRepository.excluir(id);
    }
}
