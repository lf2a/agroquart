package br.com.agropalma.agroquart.domain;

import br.com.agropalma.agroquart.AgroquartApplication;
import br.com.agropalma.agroquart.config.H2Config;
import br.com.agropalma.agroquart.domain.repository.PermissaoRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <h1>PermissaoRepositoryTests.java</h1>
 * Testando o repositorio de permissoes.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 21/11/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AgroquartApplication.class, H2Config.class})
@ActiveProfiles("test")
public class PermissaoRepositoryTests {

    @Autowired
    private PermissaoRepository permissaoRepository;

    private List<Permissao> permissoes;

    @Before
    public void init() {
        Permissao permissao1 = new Permissao();
        permissao1.setId(null);
        permissao1.setNome("ROLE_ADMIN");

        Permissao permissao2 = new Permissao();
        permissao2.setId(null);
        permissao2.setNome("ROLE_INFRA");

        permissoes = Arrays.asList(permissao1, permissao2);
    }

    @Test
    @Transactional
    @Rollback
    /**
     * Testando busca de uma permissao
     */
    public void testBuscaPermissao() {
        Permissao permissao = permissoes.get(0);
        permissaoRepository.salvarOuAtualizar(permissao);

        Permissao permissaoH2 = permissaoRepository.buscarPorId(permissao.getId());

        Assert.assertEquals(permissao.getNome(), permissaoH2.getNome());
    }

    @Test
    @Transactional
    @Rollback
    /**
     * Testando a busca de todas as permissoes
     */
    public void testBuscaPermissoes() {
        Permissao permissao1 = permissoes.get(0);
        permissaoRepository.salvarOuAtualizar(permissao1);

        Permissao permissao2 = permissoes.get(1);
        permissaoRepository.salvarOuAtualizar(permissao2);

        List<Permissao> permissoesH2 = permissaoRepository.buscarTodos();

        Assert.assertEquals(permissao2.getNome(), permissoesH2.get(1).getNome());
    }

    @Test
    @Transactional
    @Rollback
    /**
     * Testando a exclusão de uma permissao
     */
    public void testExcluindoPermissao() {
        Permissao permissao = permissoes.get(0);
        permissaoRepository.salvarOuAtualizar(permissao);

        int ret = permissaoRepository.excluir(permissao.getId());

        Assert.assertEquals(1, ret);
    }

}
