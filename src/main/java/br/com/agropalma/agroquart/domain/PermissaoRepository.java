package br.com.agropalma.agroquart.domain;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

/**
 * <h1>PermissaoRepository.java</h1>
 * Implementação de <strong><em>ICrudRepository</em></strong>, faz as operações de CRUD da entidade <strong><em>Permissao</em></strong>.
 *
 * @author Luiz Filipy
 * @version 2.0
 * @see br.com.agropalma.agroquart.domain.ICrudRepository
 * @see br.com.agropalma.agroquart.domain.Permissao
 * @since 21/11/2020
 */
@Repository
public class PermissaoRepository implements ICrudRepository<Permissao, Long> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Permissao buscarPorId(Long id) {
        // estabelecendo uma sessão
        Session session = entityManager.unwrap(Session.class);

        // buscando a permissao
        Permissao permissao = session.get(Permissao.class, id);

        return permissao;
    }

    @Override
    public List<Permissao> buscarTodos() {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // TODO: por ordenação com base no que o suario quer
        // criando a query para buscar as permissoes
        Query<Permissao> query = session.createQuery("from Permissao", Permissao.class);

        // buscando as permissoes
        return query.getResultList();
    }

    @Override
    public void salvarOuAtualizar(Permissao obj) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // salvando/atualizando o objeto
        session.saveOrUpdate(obj);
    }

    @Override
    public int excluir(Long id) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para excluir a permissao
        Query query = session.createQuery("delete from Permissao where id=:permissaoId");

        // injetando o id da permissao no sql
        query.setParameter("permissaoId", id);

        // executando o sql (excluindo a permissao)
        return query.executeUpdate();
    }
}
