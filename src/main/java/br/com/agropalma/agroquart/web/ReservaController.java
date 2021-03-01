package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Quarto;
import br.com.agropalma.agroquart.domain.Reserva;
import br.com.agropalma.agroquart.service.QuartoService;
import br.com.agropalma.agroquart.service.ReservaService;
import br.com.agropalma.agroquart.web.form.ReservaForm;
import br.com.agropalma.agroquart.web.validation.ValidacaoForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <h1>ReservaController.java</h1>
 * Controller para o endpoint reserva.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 26/11/2020
 */
@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private QuartoService quartoService;

    @PostMapping("")
    public String criarReserva(@Valid @ModelAttribute("reservaForm") ReservaForm reservaForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/?formError=" + ValidacaoForm.getUrlErrorMsg(bindingResult);
        } else if (!reservaForm.isValidDates()) {
            return "redirect:/?formError=A data de inicio deve ser menor que a data de termino";
        }

        try {
            reservaService.salvarReserva(reservaForm);
        } catch (IOException | RuntimeException e) {
            // caso o template de email nao pode ser encontrada - IOException
            // caso haja algun erro ao enviar o email - RuntimeException
            e.printStackTrace();
            return "error/500";
        }
        
        return "redirect:/?sucesso";
    }

    @GetMapping("/{reservaId}/editar")
    @PreAuthorize("hasRole('RESERVA') && hasRole('EDITAR_RESERVA')")
    public String editarReserva(@PathVariable("reservaId") String id, Map<String, Object> model) {

        Long reservaId = null;

        try {
            reservaId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Reserva> reservaOptional = Optional.ofNullable(reservaService.buscarPorId(reservaId));

        if (reservaOptional.isPresent()) {
            model.put("reserva", reservaOptional.get());

            return "reserva/editar";
        }

        return "error/404";
    }

    @PostMapping("/{reservaId}/editar")
    @PreAuthorize("hasRole('RESERVA') && hasRole('EDITAR_RESERVA')")
    public String editarReserva(@PathVariable("reservaId") String reservaId, @ModelAttribute("reservaForm") ReservaForm reservaForm, BindingResult bindingResult, Map<String, Object> model) {

        if (bindingResult.hasErrors()) {
            return "redirect:/reserva/" + reservaId + "/editar?formError=" + ValidacaoForm.getUrlErrorMsg(bindingResult);
        } else if (!reservaForm.isValidDates()) {
            return "redirect:/?formError=A data de inicio deve ser menor que a data de termino";
        }

        reservaService.atualizarReserva(reservaForm);

        return "redirect:/reserva/" + reservaId + "/editar?sucesso";
    }

    @PostMapping("/{reservaId}/excluir")
    @PreAuthorize("hasRole('RESERVA') && hasRole('EXCLUIR_RESERVA')")
    public String excluirReserva(@PathVariable("reservaId") String id) {

        Long reservaId = null;

        try {
            reservaId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Reserva> reservaOptional = Optional.ofNullable(reservaService.buscarPorId(reservaId));

        if (reservaOptional.isPresent()) {
            Reserva temp = reservaOptional.get();
            reservaService.excluir(temp.getId());

            return "redirect:/admin/reservas?excluido";
        }

        return "error/404";
    }

    @PostMapping("/{reservaId}/arquivar")
    @PreAuthorize("hasRole('RESERVA') && hasRole('EDITAR_RESERVA')")
    public String arquivar(@PathVariable("reservaId") String id) {

        Long reservaId = null;

        try {
            reservaId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Reserva> reservaOptional = Optional.ofNullable(reservaService.buscarPorId(reservaId));

        if (reservaOptional.isPresent()) {
            Reserva temp = reservaOptional.get();

            if (temp.isAutorizada()) {
                reservaService.arquivar(temp.getId());

                return "redirect:/admin/reservas?filtro=arquivadas";
            }

            return "redirect:/admin/reservas?erroArquivar";
        }

        return "error/404";
    }

    @PostMapping("/{reservaId}/autorizar")
    @PreAuthorize("hasRole('RESERVA') && hasRole('EDITAR_RESERVA')")
    public String autorizar(@PathVariable("reservaId") String id, @RequestParam("filtro") String filtro) {

        Long reservaId = null;

        try {
            reservaId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Reserva> reservaOptional = Optional.ofNullable(reservaService.buscarPorId(reservaId));

        if (reservaOptional.isPresent()) {
            Reserva temp = reservaOptional.get();

            if (Optional.ofNullable(temp.getQuarto()).isPresent()) { // verificando se já foi escolhido um quarto para a reserva
                reservaService.autorizar(temp.getId());

                return "redirect:/admin/reservas?filtro=autorizadas";
            }

            return "redirect:/admin/reservas?&erroAutorizar";
        }

        return "error/404";
    }

    @GetMapping("/{reservaId}/quarto")
    @PreAuthorize("hasRole('RESERVA') && hasRole('EDITAR_RESERVA')")
    public String escolherQuarto(@PathVariable("reservaId") String id, Map<String, Object> model) {

        Long reservaId = null;

        try {
            reservaId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Reserva> reservaOptional = Optional.ofNullable(reservaService.buscarPorId(reservaId));

        if (reservaOptional.isPresent()) {
            List<Quarto> quartoList = reservaService.buscarQuartosDisponiveis(reservaId);
            model.put("quartos", quartoList);

            return "reserva/quartos";
        }

        return "error/404";
    }

    @PostMapping("/{reservaId}/quarto/{quartoId}")
    @PreAuthorize("hasRole('RESERVA') && hasRole('EDITAR_RESERVA')")
    public String escolherQuarto(@PathVariable("reservaId") String reserva, @PathVariable("quartoId") String quarto) {

        Long reservaId;
        Long quartoId;

        try {
            reservaId = Long.parseLong(reserva);
            quartoId = Long.parseLong(quarto);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Reserva> reservaOptional = Optional.ofNullable(reservaService.buscarPorId(reservaId));
        Optional<Quarto> quartoOptional = Optional.ofNullable(quartoService.buscarPorId(quartoId));

        if (reservaOptional.isPresent() && quartoOptional.isPresent()) {
            reservaService.escolherQuarto(reservaId, quartoId);

            return "redirect:/reserva/" + reserva + "/quarto?sucesso";
        }

        return "error/404";
    }
}
