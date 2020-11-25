package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Quarto;
import br.com.agropalma.agroquart.service.QuartoService;
import br.com.agropalma.agroquart.web.form.QuartoForm;
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
 * <h1>QuartoController.java</h1>
 * Controller para o endpoint quarto.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 24/11/2020
 */
@Controller
@RequestMapping("/quarto")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @PostMapping("")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('CRIAR_HOSPEDARIA')")
    public String criarQuarto(@Valid @ModelAttribute("quartoForm") QuartoForm quartoForm, BindingResult bindingResult) {

        // verifica se tem erros
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/" + quartoForm.getHospedaria() + "/" + quartoForm.getCasa() + "/quartos?formError=" + ValidacaoForm.getUrlErrorMsg(bindingResult);
        }

        quartoService.novoQuarto(quartoForm);

        return "redirect:/admin/" + quartoForm.getHospedaria() + "/" + quartoForm.getCasa() + "/quartos?sucesso";
    }

    @GetMapping("/{quarto}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarQuarto(@PathVariable("quarto") String quarto, Map<String, Object> model) {

        Long quartoId = null;

        try {
            quartoId = Long.parseLong(quarto);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Quarto> quartoOptional = Optional.ofNullable(quartoService.buscarPorId(quartoId));

        if (quartoOptional.isPresent()) {
            model.put("quarto", quartoOptional.get());

            return "quarto/editar";
        }

        return "error/404";
    }

    @PostMapping("/{quarto}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarQuarto(@Valid @ModelAttribute("quartoForm") QuartoForm quartoForm, BindingResult bindingResult, @PathVariable("quarto") String quarto) {

        // verifica se tem erros
        if (bindingResult.hasErrors()) {
            return "redirect:/quarto/" + quarto + "/editar?formError=" + ValidacaoForm.getUrlErrorMsg(bindingResult);
        }

        quartoService.atualizarQuarto(quartoForm);

        return "redirect:/quarto/" + quarto + "/editar?sucesso";
    }

    @PostMapping("/{quarto}/excluir")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EXCLUIR_HOSPEDARIA')")
    public String excluir(@PathVariable("quarto") String quarto) {

        Long quartoId = null;

        try {
            quartoId = Long.parseLong(quarto);
        } catch (NumberFormatException e) {
            return "error/400";
        }


        Optional<Quarto> quartoOptional = Optional.ofNullable(quartoService.buscarPorId(quartoId));

        if (quartoOptional.isPresent()) {
            Quarto temp = quartoOptional.get();
            quartoService.excluir(temp.getId());

            return "redirect:/admin/" + temp.getCasa().getHospedaria().getId() + "/" + temp.getCasa().getId() + "/quartos?excluido";
        }

        return "error/404";
    }
}
