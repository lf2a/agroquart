package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Casa;
import br.com.agropalma.agroquart.domain.Quarto;
import br.com.agropalma.agroquart.service.QuartoService;
import br.com.agropalma.agroquart.web.form.QuartoForm;

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
    public String criarQuarto(@Valid @ModelAttribute("quartoForm") QuartoForm quartoForm, BindingResult bindingResult, Map<String, Object> model) throws UnsupportedEncodingException {
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

            return "redirect:/admin/" + quartoForm.getHospedaria() + "/" + quartoForm.getCasa() + "/quartos?formError=" + url;
        }

        quartoService.novoQuarto(quartoForm);

        return "redirect:/admin/" + quartoForm.getHospedaria() + "/" + quartoForm.getCasa() + "/quartos?sucesso";
    }

    @GetMapping("/{quarto}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarQuarto(@PathVariable("quarto") String quarto, Map<String, Object> model) {
        Quarto quartoObj = quartoService.buscarPorId(Long.parseLong(quarto));

        if (quartoObj != null) {
            model.put("quarto", quartoObj);

            return "quarto/editar";
        }

        return "error/404";
    }

    @PostMapping("/{quarto}/editar")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EDITAR_HOSPEDARIA')")
    public String editarQuarto(@Valid @ModelAttribute("quartoForm") QuartoForm quartoForm, BindingResult bindingResult, @PathVariable("quarto") String quarto) throws UnsupportedEncodingException {
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

            return "redirect:/quarto/" + quarto + "/editar?formError=" + url;
        }

        quartoService.atualizarQuarto(quartoForm);

        return "redirect:/quarto/" + quarto + "/editar?sucesso";
    }

    @PostMapping("/{quarto}/excluir")
    @PreAuthorize("hasRole('HOSPEDARIA') && hasRole('EXCLUIR_HOSPEDARIA')")
    public String excluir(@PathVariable("quarto") String quarto) {
        Quarto quartoObj = quartoService.buscarPorId(Long.parseLong(quarto));

        if (quartoObj != null) {
            quartoService.excluir(Long.parseLong(quarto));

            return "redirect:/admin/" + quartoObj.getCasa().getHospedaria().getId() + "/" + quartoObj.getCasa().getId() + "/quartos?excluido";
        }

        return "error/404";
    }
}
