package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Reserva;
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

import javax.validation.Valid;
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

    @PostMapping("")
    public String criarReserva(@Valid @ModelAttribute("reservaForm") ReservaForm reservaForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/?formError=" + ValidacaoForm.getUrlErrorMsg(bindingResult);
        } else if (!reservaForm.isValidDates()) {
            return "redirect:/?formError=A data de inicio deve ser menor que a data de termino";
        }

        reservaService.salvarReserva(reservaForm);

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

    // TODO: post - arquivar reserva
    // TODO: post - autorizar reserva (enviar email quando a reserva for autorizada)
    // TODO: post - excluir reserva (cancelar reserva) (enviar email quando a reserva for cancelada, se a reserva estiver arquivada não irá enviar email)
    // TODO: get - gerar relatorio completo com base em trimestres (irá ser criada um controler para relatorios)
}
