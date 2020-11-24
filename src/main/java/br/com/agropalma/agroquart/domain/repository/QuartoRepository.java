package br.com.agropalma.agroquart.domain.repository;

import br.com.agropalma.agroquart.domain.Quarto;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

/**
 * <h1>QuartoRepository.java</h1>
 * Implementação de <strong><em>ICrudRepository</em></strong>, faz as operações de CRUD da entidade <strong><em>Quarto</em></strong>.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Repository
public class QuartoRepository implements ICrudRepository<Quarto, Long> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Quarto buscarPorId(Long id) {
        // estabelecendo uma sessão
        Session session = entityManager.unwrap(Session.class);

        // buscando o quarto
        Quarto quarto = session.get(Quarto.class, id);

        return quarto;
    }

    @Override
    public List<Quarto> buscarTodos() {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para buscar os quarto
        Query<Quarto> query = session.createQuery("select q from Quarto q ORDER BY q.capacidade", Quarto.class);

        // buscando os quartos
        return query.getResultList();
    }

    public List<Quarto> buscarQuartosPorCasa(Long id) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para buscar os quarto
        Query<Quarto> query = session.createQuery("select q from Quarto q join fetch q.casa where q.casa.id=:casaId ORDER BY q.capacidade", Quarto.class);
        query.setParameter("casaId", id);

        // buscando os quartos
        return query.getResultList();
    }

    @Override
    public void salvarOuAtualizar(Quarto obj) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // salvando/atualizando o objeto
        session.saveOrUpdate(obj);
    }

    @Override
    public int excluir(Long id) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para excluir o quarto
        Query query = session.createQuery("delete from Quarto where id=:quartoId");
        query.setParameter("quartoId", id);

        // executando o sql (excluindo o quarto)
        return query.executeUpdate();
    }
}
