package br.com.agropalma.agroquart.service;

import br.com.agropalma.agroquart.domain.Quarto;
import br.com.agropalma.agroquart.domain.Reserva;
import br.com.agropalma.agroquart.domain.repository.ReservaRepository;
import br.com.agropalma.agroquart.web.form.ReservaForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Autowired
    private QuartoService quartoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MailService mailService;

    @Autowired
    private Environment env;

    @Autowired
    HtmlTemplateService htmlTemplateService;

    @Transactional
    public void salvarReserva(ReservaForm reservaForm) throws IOException, RuntimeException {

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

        List<String> emailsAdmin = usuarioService.buscarEmailDosAdmins();

        Map<String, Object> context = new HashMap<>();
        context.put("reserva", reserva);

        String html = htmlTemplateService.compilar(context, "nova-reserva");

            mailService
                    .ccAdmins(emailsAdmin) // envia para todos os admins
                    .colaborador(reserva.getEmail()) // email do usuario que solicitou a reserva
                    .assunto(env.getProperty("email.assunto.nova-reserva")) // busca o assunto da reserva do "application.properties"
                    .conteudo(html)
                    .enviar();
    }

    @Transactional
    public void atualizarReserva(ReservaForm reservaForm) {
        Reserva reserva = buscarPorId(reservaForm.getId());
        reserva.setDataInicio(reservaForm.getDateTimeInicio());
        reserva.setDataTermino(reservaForm.getDateTimeTermino());

        reservaRepository.salvarOuAtualizar(reserva);

    }

    @Transactional(readOnly = true)
    public List<Reserva> buscarReservas(Set<String> filtros, int numeroDePartida) {
        StringBuilder filtro = new StringBuilder("");

        // montando os filtros em hql
        if (!filtros.isEmpty()) {
            filtro.append("where ");

            if (filtros.contains("autorizadas")) {
                filtro.append("r.autorizada=true");
            } else {
                filtro.append("r.autorizada=false");
            }

            filtro.append(" and ");

            if (filtros.contains("arquivadas")) {
                filtro.append("r.arquivada=true");
            } else {
                filtro.append("r.arquivada=false");
            }
        }

        return reservaRepository.buscarReservas(filtro.toString(), numeroDePartida);
    }

    @Transactional(readOnly = true)
    public Reserva buscarPorId(Long id) {
        return reservaRepository.buscarPorId(id);
    }

    @Transactional
    public void excluir(Long id) {
        reservaRepository.excluir(id);
    }

    @Transactional
    public void arquivar(Long id) {
        Reserva reserva = reservaRepository.buscarPorId(id);
        reserva.setArquivar(!reserva.isArquivada());

        Quarto quarto = quartoService.buscarPorId(reserva.getQuarto().getId());

        if (reserva.isArquivada())
            quarto.setReservado(quarto.getReservado() - 1);
        else
            quarto.setReservado(quarto.getReservado() + 1);

        quartoService.atualizarOuSalvar(quarto);

        reservaRepository.salvarOuAtualizar(reserva);
    }

    @Transactional
    public void autorizar(Long id) {
        Reserva reserva = reservaRepository.buscarPorId(id);

        reserva.setAutorizada(!reserva.isAutorizada());

        reservaRepository.salvarOuAtualizar(reserva);
    }

    @Transactional(readOnly = true)
    public List<Quarto> buscarQuartosDisponiveis(Long reservaId) {
        return reservaRepository.buscarQuartosDisponiveis(reservaId);
    }

    @Transactional
    public void escolherQuarto(Long reservaId, Long quartoId) {
        Reserva reserva = buscarPorId(reservaId);

        Quarto quarto = quartoService.buscarPorId(quartoId);
        quarto.setReservado(quarto.getReservado() + 1);
        quartoService.atualizarOuSalvar(quarto);

        reserva.setQuarto(quarto);

        reservaRepository.salvarOuAtualizar(reserva);
    }

    @Transactional(readOnly = true)
    public Long quantidadeDeReservas() {
        return reservaRepository.quantidadeDeReservas();
    }
}
