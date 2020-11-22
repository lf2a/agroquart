package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Usuario;
import br.com.agropalma.agroquart.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * <h1>AdminController.java</h1>
 * Controller para o endpoint admin.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 21/11/2020
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN') && hasRole('USUARIO')")
    public String usuarios(Map<String, Object> model) {
        List<Usuario> usuarioList = usuarioService.buscarTodos();
        model.put("usuarios", usuarioList);

        return "admin/usuarios";
    }
}
