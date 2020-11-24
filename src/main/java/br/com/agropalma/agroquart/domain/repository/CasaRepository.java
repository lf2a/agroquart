package br.com.agropalma.agroquart.domain.repository;

import br.com.agropalma.agroquart.domain.Casa;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

/**
 * <h1>CasaRepository.java</h1>
 * Implementação de <strong><em>ICrudRepository</em></strong>, faz as operações de CRUD da entidade <strong><em>Casa</em></strong>.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Repository
public class CasaRepository implements ICrudRepository<Casa, Long> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Casa buscarPorId(Long id) {
        // estabelecendo uma sessão
        Session session = entityManager.unwrap(Session.class);

        // buscando a casa
        Query<Casa> query = session.createQuery("select c from Casa c join fetch c.hospedaria where c.id=:casaId", Casa.class);
        query.setParameter("casaId", id);

        return query.getSingleResult();
    }

    @Override
    public List<Casa> buscarTodos() {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para buscar as casa
        Query<Casa> query = session.createQuery("select c from Casa c ORDER BY c.numero", Casa.class);

        // buscando as casa
        return query.getResultList();
    }

    public List<Casa> buscarCasasPorHospedaria(Long id) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para buscar as casa
        Query<Casa> query = session.createQuery("select c from Casa c where c.hospedaria.id=:hospedariaId ORDER BY c.numero", Casa.class);
        query.setParameter("hospedariaId", id);

        // buscando as casa
        return query.getResultList();
    }

    @Override
    public void salvarOuAtualizar(Casa obj) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // salvando/atualizando o objeto
        session.saveOrUpdate(obj);
    }

    @Override
    public int excluir(Long id) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para excluir a casa
        Query query = session.createQuery("delete from Casa where id=:casaId");
        query.setParameter("casaId", id);

        // executando o sql (excluindo a casa)
        return query.executeUpdate();
    }
}
