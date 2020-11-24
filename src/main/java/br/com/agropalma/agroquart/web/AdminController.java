package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Casa;
import br.com.agropalma.agroquart.domain.Hospedaria;
import br.com.agropalma.agroquart.domain.Quarto;
import br.com.agropalma.agroquart.service.CasaService;
import br.com.agropalma.agroquart.service.HospedariaService;
import br.com.agropalma.agroquart.service.PermissaoService;
import br.com.agropalma.agroquart.service.QuartoService;
import br.com.agropalma.agroquart.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private HospedariaService hospedariaService;

    @Autowired
    private CasaService casaService;

    @Autowired
    private QuartoService quartoService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN') && hasRole('USUARIO')")
    public String usuarios(Map<String, Object> model) {
        model.put("usuarios", usuarioService.buscarTodos());
        model.put("permissoes", permissaoService.buscarTodos());

        return "admin/usuarios";
    }

    @GetMapping("/hospedarias")
    @PreAuthorize("hasRole('ADMIN') && hasRole('HOSPEDARIA')")
    public String hospedarias(Map<String, Object> model) {
        List<Hospedaria> hospedariaList = hospedariaService.buscarTodos();

        model.put("hospedarias", hospedariaList);

        return "admin/hospedarias";
    }

    @GetMapping("/{hospedaria}/casas")
    @PreAuthorize("hasRole('ADMIN') && hasRole('HOSPEDARIA')")
    public String casas(@PathVariable("hospedaria") String hospedaria, Map<String, Object> model) {
        List<Casa> casas = casaService.buscarCasasPorHospedaria(Long.parseLong(hospedaria));

        model.put("casas", casas);

        return "admin/casas";
    }

    @GetMapping("/{hospedaria}/{casa}/quartos")
    @PreAuthorize("hasRole('ADMIN') && hasRole('HOSPEDARIA')")
    public String quartos(@PathVariable("casa") String casa, Map<String, Object> model) {
        List<Quarto> quartos = quartoService.buscarQuartosPorCasa(Long.parseLong(casa));

        model.put("quartos", quartos);

        return "admin/quartos";
    }
}
