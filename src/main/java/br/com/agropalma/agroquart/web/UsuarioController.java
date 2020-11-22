package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.service.UsuarioService;
import br.com.agropalma.agroquart.web.form.UsuarioForm;
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
 * <h1>UsuarioController.java</h1>
 * Controller para operações de usuario.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 21/11/2020
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("")
    @PreAuthorize("hasRole('USUARIO') && hasRole('CRIAR_USUARIO')")
    public String criarUsuario(@Valid @ModelAttribute("usuarioForm") UsuarioForm usuarioForm, BindingResult bindingResult) throws UnsupportedEncodingException {

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

            return "redirect:/admin/usuarios?formError=" + url;
        }

        usuarioService.novoUsuario(usuarioForm);


        return "redirect:/admin/usuarios?sucesso";
    }

    // ver o form
    @GetMapping("/{usuario}/editar")
    @PreAuthorize("hasRole('USUARIO') && hasRole('EDITAR_USUARIO')")
    public String editarUsuario(@PathVariable("usuario") String usuario, Map<String, Object> model) {
        return "index";
    }

    // receber o form
    @PostMapping("/{usuario}/editar")
    @PreAuthorize("hasRole('USUARIO') && hasRole('EDITAR_USUARIO')")
    public String editarUsuario(@PathVariable("usuario") String usuario) {
        return "redirect:/";
    }

    @PostMapping("/{usuario}/excluir")
    @PreAuthorize("hasRole('USUARIO') && hasRole('EXCLUIR_USUARIO')")
    public String excluirUsuario(@PathVariable("usuario") String usuario) {
        return "redirect:/";
    }
}
