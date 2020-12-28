package br.com.agropalma.agroquart.domain.repository;

import br.com.agropalma.agroquart.domain.Quarto;
import br.com.agropalma.agroquart.domain.Reserva;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

/**
 * <h1>ReservaRepository.java</h1>
 * Implementação de <strong><em>ICrudRepository</em></strong>, faz as operações de CRUD da entidade <strong><em>Reserva</em></strong>.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 26/11/2020
 */
@Repository
public class ReservaRepository implements ICrudRepository<Reserva, Long> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Reserva buscarPorId(Long id) {
        // estabelecendo uma sessão
        Session session = entityManager.unwrap(Session.class);

        // buscando a reserva
        Reserva reserva = session.get(Reserva.class, id);

        return reserva;
    }

    @Override
    public List<Reserva> buscarTodos() {
        return null;
    }

    public List<Reserva> buscarReservas(String filtro) {
        // TODO: fazer paginação
        Session session = entityManager.unwrap(Session.class);

        Query<Reserva> query = session.createQuery("select r from Reserva r " + filtro + " order by r.criadaEm desc", Reserva.class);

        return query.getResultList();
    }

    @Override
    public void salvarOuAtualizar(Reserva obj) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // salvando/atualizando o objeto
        session.saveOrUpdate(obj);
    }

    @Override
    public int excluir(Long id) {
        // estabelecendo a sessão
        Session session = entityManager.unwrap(Session.class);

        // criando a query para excluir a reserva
        Query query = session.createQuery("delete from Reserva where id=:reservaId");
        query.setParameter("reservaId", id);

        // executando o sql (excluindo a reserva)
        return query.executeUpdate();
    }

    public List<Quarto> buscarQuartosDisponiveis(Long reservaId) {
        // TODO: mover para o repositorio de quarto
        Session session = entityManager.unwrap(Session.class);

        Reserva temp = buscarPorId(reservaId);

        Query<Quarto> query = session.createQuery("select q from Quarto q where q.reservado < q.capacidade and q.casa.sexo=:reservaSexo", Quarto.class);
        query.setParameter("reservaSexo", temp.getSexo());

        return query.getResultList();
    }
}
