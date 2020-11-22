package br.com.agropalma.agroquart.service;

import br.com.agropalma.agroquart.domain.Permissao;
import br.com.agropalma.agroquart.domain.Usuario;
import br.com.agropalma.agroquart.domain.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>UsuarioService.java</h1>
 * Classe de serviço do usuário (contém regras de negócios).
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 21/11/2020
 */
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Permissao> permissoes) {
        return permissoes.stream().map(permissao -> new SimpleGrantedAuthority(permissao.getNome())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.buscarUsuarioPorUsername(s);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário e/ou senha inválidos!");
        }

        return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getSenha(),
                mapRolesToAuthorities(usuario.getPermissoes()));
    }

    /**
     * Recebe o ID de tipo I e irá buscar o objeto no banco de dados e irá retorna-lo.
     *
     * @param id O id do objeto a ser buscado.
     * @return Null ou o objeto de tipo T retornado do banco de dados.
     */
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }

    /**
     * Irá buscar o usuário pelo nome de usuário.
     *
     * @param username O nome de usuário.
     * @return O objeto usuário ou null.
     */
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorUsername(String username) {
        return usuarioRepository.buscarUsuarioPorUsername(username);
    }

    /**
     * Irá buscar todos os objetos de tipo T do banco de dados.
     *
     * @return Uma lista com todos os objetos ou uma lista vazia.
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.buscarTodos();
    }

    /**
     * Irá receber um objeto de tipo T para salva-lo ou atualiza-lo no banco de dados.
     *
     * @param obj O objeto a ser salvo ou atualizado.
     */
    @Transactional
    public void atualizarUsuario(Usuario obj) {
        usuarioRepository.salvarOuAtualizar(obj);
    }

    /**
     * Irá excluir o objeto do banco de dados com base no ID de tipo I fornecido.
     *
     * @param id O ID do objeto a ser excluido do banco de dados.
     */
    @Transactional
    public int excluir(Long id) {
        return usuarioRepository.excluir(id);
    }
}
