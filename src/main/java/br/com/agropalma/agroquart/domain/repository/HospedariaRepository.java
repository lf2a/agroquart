package br.com.agropalma.agroquart.domain.repository;

import br.com.agropalma.agroquart.domain.Hospedaria;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

/**
 * <h1>HospedariaRepository.java</h1>
 * Implementação de <strong><em>ICrudRepository</em></strong>, faz as operações de CRUD da entidade <strong><em>Hospedaria</em></strong>.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Repository
public class HospedariaRepository implements ICrudRepository<Hospedaria, Long> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Hospedaria buscarPorId(Long id) {
        // estabelecendo uma sessão
        Session session = entityManager.unwrap(Session.class);

        // buscando a hospedaria
        Hospedaria hospedaria = session.get(Hospedaria.class, id);

        return hospedaria;
    }

    @Override
    public List<Hospedaria> buscarTodos() {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para buscar as hospedaria
        Query<Hospedaria> query = session.createQuery("select h from Hospedaria h ORDER BY h.nomeHospedaria", Hospedaria.class);

        // buscando as hospedaria
        return query.getResultList();
    }

    @Override
    public void salvarOuAtualizar(Hospedaria obj) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // salvando/atualizando o objeto
        session.saveOrUpdate(obj);
    }

    @Override
    public int excluir(Long id) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para excluir a hospedaria
        Query query = session.createQuery("delete from Hospedaria where id=:hospedariaId");
        query.setParameter("hospedariaId", id);

        // executando o sql (excluindo a hospedaria)
        return query.executeUpdate();
    }
}
