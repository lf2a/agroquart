package br.com.agropalma.agroquart.domain.repository;

import br.com.agropalma.agroquart.domain.Reserva;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
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

    public List<Reserva> buscarReservasAutorizadas(boolean autorizada) {
        // estabelecendo uma sessão
        Session session = entityManager.unwrap(Session.class);

        Query<Reserva> query = session.createQuery("select r from Reserva r where r.autorizada=:autorizada order by r.criadaEm asc", Reserva.class);
        query.setParameter("autorizada", autorizada);

        return query.getResultList();
    }

    public List<Reserva> buscarReservasArquivadas(boolean arquivada) {
        Session session = entityManager.unwrap(Session.class);

        Query<Reserva> query = session.createQuery("select r from Reserva r where r.arquivada=:arquivada order by r.criadaEm desc", Reserva.class);
        query.setParameter("arquivada", arquivada);

        return query.getResultList();
    }

    public List<Reserva> buscarReservasEmAndamento() {
        Session session = entityManager.unwrap(Session.class);

        Query<Reserva> query = session.createQuery("select r from Reserva r where r.arquivada=false and r.autorizada=true and r.dataInicio <= :hoje order by r.criadaEm desc", Reserva.class);
        query.setParameter("hoje", LocalDateTime.now());

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
}
