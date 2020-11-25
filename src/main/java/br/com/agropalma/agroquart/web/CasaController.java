package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Casa;
import br.com.agropalma.agroquart.service.CasaService;
import br.com.agropalma.agroquart.web.form.CasaForm;
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
 * <h1>CasaController.java</h1>
 * Controller para o endpoint casa.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Controller
@RequestMapping("/casa")
public class CasaController {

    @Autowired
    private CasaService casaService;

    @PostMapping("")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('CRIAR_HOSPEDARIA')")
    public String criarCasa(@Valid @ModelAttribute("casaForm") CasaForm casaForm, BindingResult bindingResult) {

        // verifica se tem erros
        if (bindingResult.hasErrors()) {
            return ValidacaoForm.getUrlErrorMsg(bindingResult);
        }

        casaService.novaCasa(casaForm);

        return "redirect:/admin/" + casaForm.getHospedaria() + "/casas?sucesso";
    }

    @GetMapping("/{casa}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarCasa(@PathVariable("casa") String casa, Map<String, Object> model) {

        Long casaId = null;

        try {
            casaId = Long.parseLong(casa);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Casa> casaOptional = Optional.ofNullable(casaService.buscarPorId(casaId));

        if (casaOptional.isPresent()) {
            model.put("casa", casaOptional.get());

            return "casa/editar";
        }

        return "error/404";
    }

    @PostMapping("/{casa}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarCasa(@Valid @ModelAttribute("casaForm") CasaForm casaForm, BindingResult bindingResult, @PathVariable("casa") String casa) {

        // verifica se tem erros
        if (bindingResult.hasErrors()) {
            return ValidacaoForm.getUrlErrorMsg(bindingResult);
        }

        casaService.atualizarCasa(casaForm);

        return "redirect:/casa/" + casa + "/editar?sucesso";
    }

    @PostMapping("/{casa}/excluir")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EXCLUIR_HOSPEDARIA')")
    public String excluir(@PathVariable("casa") String casa) {

        Long casaId = null;

        try {
            casaId = Long.parseLong(casa);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Casa> casaOptional = Optional.ofNullable(casaService.buscarPorId(casaId));

        if (casaOptional.isPresent()) {
            Casa temp = casaOptional.get();
            casaService.excluir(temp.getId());

            return "redirect:/admin/" + temp.getHospedaria().getId() + "/casas?excluido=" + temp.getNumero();
        }

        return "error/404";
    }
}
