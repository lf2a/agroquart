package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Usuario;
import br.com.agropalma.agroquart.service.PermissaoService;
import br.com.agropalma.agroquart.service.UsuarioService;
import br.com.agropalma.agroquart.web.form.UsuarioForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        // TODO: fazer validação para matricula, username e email
        usuarioService.novoUsuario(usuarioForm);


        return "redirect:/admin/usuarios?sucesso";
    }

    // ver o form
    @GetMapping("/{matricula}/editar")
    @PreAuthorize("hasRole('USUARIO') && hasRole('EDITAR_USUARIO')")
    public String editarUsuario(@PathVariable("matricula") String usuario, Map<String, Object> model) {
        Usuario usuarioObj = usuarioService.buscarPorId(Long.parseLong(usuario));

        if (usuarioObj != null) {
            model.put("usuario", usuarioObj);
            model.put("permissoes", permissaoService.buscarTodos());

            return "usuario/formulario";
        }

        return "redirect:/404";
    }

    // receber o form
    @PostMapping("/{matricula}/editar")
    @PreAuthorize("hasRole('USUARIO') && hasRole('EDITAR_USUARIO')")
    public String editarUsuario(@PathVariable("matricula") String usuario, @Valid @ModelAttribute("usuarioForm") UsuarioForm usuarioForm, BindingResult bindingResult) throws UnsupportedEncodingException {

        Usuario usuarioObj = usuarioService.buscarPorId(Long.parseLong(usuario));

        if (usuarioObj != null) {
            if (bindingResult.getFieldErrors().size() == 2 && bindingResult.getFieldError("senha") != null && bindingResult.getFieldError("senha2") != null) {
                // o form so tem 2 erros e esses erros são as senhas em branco

                usuarioForm.setSenha(usuarioObj.getSenha());

            } else if (bindingResult.hasErrors()) {
                // o form tem erros

                StringBuilder stringBuilder = new StringBuilder();

                // busca todos os erros
                List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
                fieldErrorList.forEach(f -> {
                    if (f.getField() == "senha" || f.getField() == "senha2") {
                        // busca todas as mensagens de erros em uma string separados por um '@' (serve para fazer um split e iterar sobre)
                        stringBuilder.append(f.getDefaultMessage() + "@");
                    }
                });

                // converte a string para uma url com encode utf-8
                String url = URLEncoder.encode(stringBuilder.toString(), "UTF-8");

                return "redirect:/usuario/" + usuario + "/editar?formError=" + url;
            } else if (usuarioForm.getSenha() != null) {
                // o form não tem erros e a senha não está em branco

                usuarioForm.setSenha(passwordEncoder.encode(usuarioForm.getSenha()));
            }

            // TODO: fazer validação para username e email
            usuarioService.atualizarUsuario(usuarioForm, usuarioObj);


            return "redirect:/usuario/" + usuario + "/editar?sucesso";
        }

        return "redirect:/404";
    }

    @PostMapping("/{matricula}/excluir")
    @PreAuthorize("hasRole('USUARIO') && hasRole('EXCLUIR_USUARIO')")
    public String excluirUsuario(@PathVariable("matricula") String usuario) {
        Usuario usuarioObj = usuarioService.buscarPorId(Long.parseLong(usuario));

        if (usuarioObj != null) {
            usuarioService.excluir(usuarioObj.getMatricula());
            return "redirect:/admin";
        }

        return "redirect:/404";
    }
}
