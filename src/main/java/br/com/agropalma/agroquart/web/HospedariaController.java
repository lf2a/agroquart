package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Hospedaria;
import br.com.agropalma.agroquart.service.HospedariaService;
import br.com.agropalma.agroquart.web.form.HospedariaForm;
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
 * <h1>HospedariaController.java</h1>
 * Controller para o endpoint hospedaria.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Controller
@RequestMapping("/hospedaria")
public class HospedariaController {

    @Autowired
    private HospedariaService hospedariaService;

    @PostMapping("")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('CRIAR_HOSPEDARIA')")
    public String criarHospedaria(@Valid @ModelAttribute("hospedariaForm") HospedariaForm hospedariaForm, BindingResult bindingResult) {

        // verifica se tem erros
        if (bindingResult.hasErrors()) {
            return ValidacaoForm.getUrlErrorMsg(bindingResult);
        }

        hospedariaService.novaHospedaria(hospedariaForm);

        return "redirect:/admin/hospedarias?sucesso";
    }

    @GetMapping("/{hospedaria}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarHospedaria(@PathVariable("hospedaria") String hospedaria, Map<String, Object> model) {

        Long hospedariaId = null;

        try {
            hospedariaId = Long.parseLong(hospedaria);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        model.put("hospedaria", hospedariaService.buscarPorId(hospedariaId));

        return "hospedaria/editar";
    }

    @PostMapping("/{hospedaria}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarHospedaria(@Valid @ModelAttribute("hospedariaForm") HospedariaForm hospedariaForm, BindingResult bindingResult, @PathVariable("hospedaria") String hospedaria) {

        // verifica se tem erros
        if (bindingResult.hasErrors()) {
            return ValidacaoForm.getUrlErrorMsg(bindingResult);
        }

        hospedariaService.atualizarHospedaria(hospedariaForm);

        return "redirect:/hospedaria/" + hospedaria + "/editar?sucesso";
    }

    @PostMapping("/{hospedaria}/excluir")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EXCLUIR_HOSPEDARIA')")
    public String excluir(@PathVariable("hospedaria") String hospedaria) {

        Long hospedariaId = null;

        try {
            hospedariaId = Long.parseLong(hospedaria);
        } catch (NumberFormatException e) {
            return "error/400";
        }

        Optional<Hospedaria> hospedariaOptional = Optional.ofNullable(hospedariaService.buscarPorId(hospedariaId));

        if (hospedariaOptional.isPresent()) {
            Hospedaria temp = hospedariaOptional.get();

            hospedariaService.excluir(temp.getId());

            return "redirect:/admin/hospedarias?excluido=" + temp.getNomeHospedaria();
        }

        return "error/404";
    }
}
