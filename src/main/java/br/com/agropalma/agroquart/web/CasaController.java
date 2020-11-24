package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Casa;
import br.com.agropalma.agroquart.service.CasaService;
import br.com.agropalma.agroquart.web.form.CasaForm;

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
    public String criarCasa(@Valid @ModelAttribute("casaForm") CasaForm casaForm, BindingResult bindingResult, Map<String, Object> model) throws UnsupportedEncodingException {

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

            return "redirect:/admin/" + casaForm.getHospedaria() + "/casas?formError=" + url;
        }

        casaService.novaCasa(casaForm);

        return "redirect:/admin/" + casaForm.getHospedaria() + "/casas?sucesso";
    }

    @GetMapping("/{casa}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarCasa(@PathVariable("casa") String casa, Map<String, Object> model) {
        Casa casaObj = casaService.buscarPorId(Long.parseLong(casa));

        if(casaObj != null) {
            model.put("casa", casaObj);

            return "casa/editar";
        }

        return "error/404";
    }

    @PostMapping("/{casa}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarCasa(@Valid @ModelAttribute("casaForm") CasaForm casaForm, BindingResult bindingResult, @PathVariable("casa") String casa, Map<String, Object> model) throws UnsupportedEncodingException {

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

            return "redirect:/casa/" + casa + "/editar?formError=" + url;
        }

        casaService.atualizarCasa(casaForm);

        return "redirect:/casa/" + casa + "/editar?sucesso";
    }

    @PostMapping("/{casa}/excluir")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EXCLUIR_HOSPEDARIA')")
    public String excluir(@PathVariable("casa") String casa) {
        Casa casaObj = casaService.buscarPorId(Long.parseLong(casa));

        if (casaObj != null) {
            casaService.excluir(Long.parseLong(casa));

            return "redirect:/admin/" + casaObj.getHospedaria().getId() + "/casas?excluido=" + casaObj.getNumero();
        }

        return "error/404";
    }
}
