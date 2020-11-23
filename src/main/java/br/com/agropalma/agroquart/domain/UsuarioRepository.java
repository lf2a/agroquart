package br.com.agropalma.agroquart.domain;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.sql.SQLException;
import java.util.List;

/**
 * <h1>UsuarioRepository.java</h1>
 * Implementação de <strong><em>ICrudRepository</em></strong>, faz as operações de CRUD da entidade <strong><em>Usuario</em></strong>.
 *
 * @author Luiz Filipy
 * @version 2.0
 * @see br.com.agropalma.agroquart.domain.ICrudRepository
 * @see br.com.agropalma.agroquart.domain.Usuario
 * @since 20/11/2020
 */
@Repository
public class UsuarioRepository implements ICrudRepository<Usuario, Long> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Usuario buscarPorId(Long id) {
        // estabelecendo uma sessão
        Session session = entityManager.unwrap(Session.class);

        // buscando usuario
        Usuario usuario = session.get(Usuario.class, id);

        return usuario;
    }

    /**
     * Irá buscar o usuário pelo nome de usuário.
     *
     * @param username O nome de usuário.
     * @return O objeto usuário ou null.
     */
    public Usuario buscarUsuarioPorUsername(String username) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para buscar o usuário
        Query<Usuario> query = session.createQuery("from Usuario where usuario=:usuario", Usuario.class);
        query.setParameter("usuario", username);

        // buscando usuario
        Usuario usuario;
        try {
            usuario = query.getSingleResult();
        } catch (Exception e) {
            usuario = null;
        }

        return usuario;
    }

    public Usuario verificarEmailUsername(String usuario, String email) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para buscar o usuário
        Query<Usuario> query = session.createQuery("from Usuario where usuario=:usuario or email=:email", Usuario.class);
        query.setParameter("usuario", usuario);
        query.setParameter("email", email);

        Usuario usuarioObj;
        try {
            usuarioObj = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

        return usuarioObj;
    }

    @Override
    public List<Usuario> buscarTodos() {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // TODO: por ordenação com base no que o suario quer
        // criando a query para buscar os usuários
        Query<Usuario> query = session.createQuery("from Usuario", Usuario.class);

        // buscando os usuários
        return query.getResultList();
    }

    @Override
    public void salvarOuAtualizar(Usuario obj) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // salvando/atualizando o objeto
        session.saveOrUpdate(obj);
    }

    public void salvar(Usuario obj) throws SQLException {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // salvando/atualizando o objeto
        session.save(obj);
    }

    @Override
    public int excluir(Long id) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para excluir o usuario
        Query query = session.createQuery("delete from Usuario where matricula=:usuarioId");

        // injetando o id do usuário no sql
        query.setParameter("usuarioId", id);

        // executando o sql (excluindo o usuário)
        return query.executeUpdate();
    }
}
