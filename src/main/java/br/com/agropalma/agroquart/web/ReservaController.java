package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.service.ReservaService;
import br.com.agropalma.agroquart.web.form.ReservaForm;
import br.com.agropalma.agroquart.web.validation.ValidacaoForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
        } else if (reservaForm.isValidDates()) {
            return "redirect:/?formError=A data de inicio deve ser menor que a data de termino";
        }

        reservaService.salvarReserva(reservaForm);

        return "redirect:/?sucesso";
    }

    // TODO: get - atualizar reserva (ver o html)
    // TODO: post - atualizar reserva (receber o form)
    // TODO: post - arquivar reserva
    // TODO: post - autorizar reserva
    // TODO: post - excluir reserva
    // TODO: get - gerar relatorio completo com base em trimestres
    // TODO: post - cancelar reserva
}
