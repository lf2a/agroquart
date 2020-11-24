package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Hospedaria;
import br.com.agropalma.agroquart.service.HospedariaService;
import br.com.agropalma.agroquart.web.form.HospedariaForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

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
    public String criarHospedaria(@Valid @ModelAttribute("hospedariaForm") HospedariaForm hospedariaForm, BindingResult bindingResult, Map<String, Object> model) throws UnsupportedEncodingException {

        // verifica se tem erros
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();

            // busca todos os erros
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            fieldErrorList.forEach(f -> {
                // busca todas as mensagens de erros em uma string separados por um '@' (serve para fazer um split e iterar sobre)
                stringBuilder.append(f.getDefaultMessage() + "@");
            });

            // converte a string para uma url com encode utf-8
            String url = URLEncoder.encode(stringBuilder.toString(), "UTF-8");

            return "redirect:/admin/hospedarias?formError=" + url;
        }

        hospedariaService.novaHospedaria(hospedariaForm);

        return "redirect:/admin/hospedarias?sucesso";
    }

    @GetMapping("/{hospedaria}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarHospedaria(@PathVariable("hospedaria") String hospedaria, Map<String, Object> model) {
        Hospedaria hospedariaObj = hospedariaService.buscarPorId(Long.parseLong(hospedaria));

        model.put("hospedaria", hospedariaObj);

        return "hospedaria/editar";
    }

    @PostMapping("/{hospedaria}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarHospedaria(@Valid @ModelAttribute("hospedariaForm") HospedariaForm hospedariaForm, BindingResult bindingResult, @PathVariable("hospedaria") String hospedaria, Map<String, Object> model) throws UnsupportedEncodingException {

        // verifica se tem erros
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();

            // busca todos os erros
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            fieldErrorList.forEach(f -> {
                // busca todas as mensagens de erros em uma string separados por um '@' (serve para fazer um split e iterar sobre)
                stringBuilder.append(f.getDefaultMessage() + "@");
            });

            // converte a string para uma url com encode utf-8
            String url = URLEncoder.encode(stringBuilder.toString(), "UTF-8");

            return "redirect:/hospedaria/" + hospedaria + "/editar?formError=" + url;
        }

        hospedariaService.atualizarHospedaria(hospedariaForm);

        return "redirect:/hospedaria/" + hospedaria + "/editar?sucesso";
    }

    @PostMapping("/{hospedaria}/excluir")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EXCLUIR_HOSPEDARIA')")
    public String excluir(@PathVariable("hospedaria") String hospedaria) {
        Hospedaria hospedariaObj = hospedariaService.buscarPorId(Long.parseLong(hospedaria));

        if (hospedariaObj != null) {
            hospedariaService.excluir(Long.parseLong(hospedaria));

            return "redirect:/admin/hospedarias?excluido=" + hospedariaObj.getNomeHospedaria();
        }

        return "error/404";
    }
}
