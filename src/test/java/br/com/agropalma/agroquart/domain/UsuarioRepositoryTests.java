package br.com.agropalma.agroquart.domain;

import br.com.agropalma.agroquart.AgroquartApplication;
import br.com.agropalma.agroquart.config.H2Config;

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
import java.util.Date;
import java.util.List;

/**
 * <h1>UsuarioRepositoryTests.java</h1>
 * Testando o repositorio de usuários.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @see br.com.agropalma.agroquart.domain.Usuario
 * @since 20/11/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AgroquartApplication.class, H2Config.class})
@ActiveProfiles("test")
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private List<Usuario> usuarios;

    @Before
    public void init() {
        Usuario usuario1 = new Usuario.Builder()
                .usuario("luiz.filipy")
                .matricula(12345L)
                .nomeCompleto("Luiz Filipy")
                .empresa("Java")
                .email("luizfilipy@email.com")
                .senha("1234")
                .ativo(true)
                .ultimoLogin(new Date())
                .criadaEm(new Date())
                .permissoes(null)
                .build();

        Usuario usuario2 = new Usuario.Builder()
                .usuario("ana.paula")
                .matricula(54321L)
                .nomeCompleto("Ana Paula")
                .empresa("Python")
                .email("anapaula@email.com")
                .senha("4321")
                .ativo(false)
                .ultimoLogin(new Date())
                .criadaEm(new Date())
                .permissoes(null)
                .build();

        usuarios = Arrays.asList(usuario1, usuario2);

        usuarioRepository.salvarOuAtualizar(usuario1);
        usuarioRepository.salvarOuAtualizar(usuario2);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    /**
     * Testando busca de um usuario
     */
    public void testBuscaUsuario() {
        Usuario usuario = usuarios.get(0);

        usuarioRepository.salvarOuAtualizar(usuario);

        Usuario usuarioH2 = usuarioRepository.buscarPorId(12345L);

        Assert.assertEquals(usuario.getSenha(), usuarioH2.getSenha());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    /**
     * Testando a busca de varios usuários.
     */
    public void testBuscaUsuarios() {
        Usuario usuario1 = usuarios.get(0);
        usuarioRepository.salvarOuAtualizar(usuario1);

        Usuario usuario2 = usuarios.get(1);
        usuarioRepository.salvarOuAtualizar(usuario2);

        List<Usuario> usuarios = usuarioRepository.buscarTodos();

        Assert.assertEquals(usuario1.getEmail(), usuarios.get(0).getEmail());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    /**
     * Testando a exclusão de um usuário no banco de dados.
     */
    public void testE() {
        int ret = usuarioRepository.excluir(usuarios.get(0).getMatricula());

        Assert.assertEquals(1, ret);
    }
}
