package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Usuario;
import br.com.agropalma.agroquart.service.PermissaoService;
import br.com.agropalma.agroquart.service.UsuarioService;
import br.com.agropalma.agroquart.web.form.UsuarioForm;
import br.com.agropalma.agroquart.web.validation.ValidacaoForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
            return "redirect:/admin/usuarios?formError=" + ValidacaoForm.getUrlErrorMsg(bindingResult);
        }

        if (usuarioService.verificarUsuario(usuarioForm.getUsuario()))
            return "redirect:/admin/usuarios?formError=" + URLEncoder.encode("Usuário já existe", "UTF-8");

        if (usuarioService.verificarEmail(usuarioForm.getEmail()))
            return "redirect:/admin/usuarios?formError=" + URLEncoder.encode("Email já existe", "UTF-8");

        if (usuarioService.verificarMatricula(usuarioForm.getMatricula()))
            return "redirect:/admin/usuarios?formError=" + URLEncoder.encode("Matrícula já existe", "UTF-8");

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

                return "redirect:/usuario/" + usuario + "/editar?formError=" + ValidacaoForm.getUrlErrorMsg(bindingResult);

            } else if (usuarioForm.getSenha() != null) {
                // o form não tem erros e a senha não está em branco
                usuarioForm.setSenha(passwordEncoder.encode(usuarioForm.getSenha()));
            }

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
            return "redirect:/admin/usuarios?excluido";
        }

        return "redirect:/404";
    }
}
