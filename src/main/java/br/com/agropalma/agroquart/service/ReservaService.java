package br.com.agropalma.agroquart.service;

import br.com.agropalma.agroquart.domain.Reserva;
import br.com.agropalma.agroquart.domain.repository.ReservaRepository;
import br.com.agropalma.agroquart.web.form.ReservaForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <h1>ReservaService.java</h1>
 * Classe de serviço da reserva (contém regras de negócios).
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 27/11/2020
 */
@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional
    public void salvarReserva(ReservaForm reservaForm) {

        Reserva reserva = new Reserva.Builder()
                .nomeCompleto(reservaForm.getNomeCompleto())
                .matricula(reservaForm.getMatricula())
                .gerenteResponsavel(reservaForm.getGerenteResponsavel())
                .email(reservaForm.getEmail())
                .empresa(reservaForm.getEmpresa())
                .dataInicio(reservaForm.getDateTimeInicio())
                .dataTermino(reservaForm.getDateTimeTermino())
                .motivo(reservaForm.getMotivo())
                .cargo(reservaForm.getCargo())
                .criadaEm(LocalDateTime.now().withNano(0))
                .tipo(reservaForm.getTipoReserva())
                .sexo(reservaForm.getSexo())
                .build();

        reservaRepository.salvarOuAtualizar(reserva);

        // TODO: enviar email para quem solicitou e para os admins que tem permissão de reservar.
    }

    @Transactional
    public void atualizarReserva(ReservaForm reservaForm) {
        Reserva reserva = buscarPorId(reservaForm.getId());
        reserva.setDataInicio(reservaForm.getDateTimeInicio());
        reserva.setDataTermino(reservaForm.getDateTimeTermino());

        reservaRepository.salvarOuAtualizar(reserva);

        // TODO: enviar email para quem solicitou e para os admins que tem permissão de reservar.
    }

    @Transactional(readOnly = true)
    public List<Reserva> buscarReservasAutorizadas(boolean autorizada) {
        return reservaRepository.buscarReservasAutorizadas(autorizada);
    }

    @Transactional(readOnly = true)
    public List<Reserva> buscarReservasArquivadas(boolean arquivada) {
        return reservaRepository.buscarReservasArquivadas(arquivada);
    }

    @Transactional(readOnly = true)
    public List<Reserva> buscarReservasEmAndamento() {
        return reservaRepository.buscarReservasEmAndamento();
    }

    @Transactional(readOnly = true)
    public Reserva buscarPorId(Long id) {
        return reservaRepository.buscarPorId(id);
    }
}
