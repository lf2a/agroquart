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
import java.util.Optional;

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
        model.put("hospedarias", hospedariaService.buscarTodos());

        return "admin/hospedarias";
    }

    @GetMapping("/{hospedaria}/casas")
    @PreAuthorize("hasRole('ADMIN') && hasRole('HOSPEDARIA')")
    public String casas(@PathVariable("hospedaria") String hospedaria, Map<String, Object> model) {
        Long hospedariaId = null;

        try {
            hospedariaId = Long.parseLong(hospedaria);
        } catch (NumberFormatException e) { // se tivar letra, espaço ou simbolos irá retorna uma pagina de erro.
            return "error/400"; // bad request: https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/400
        }

        model.put("casas", casaService.buscarCasasPorHospedaria(hospedariaId));

        return "admin/casas";
    }

    @GetMapping("/{hospedaria}/{casa}/quartos")
    @PreAuthorize("hasRole('ADMIN') && hasRole('HOSPEDARIA')")
    public String quartos(@PathVariable("hospedaria") String hospedaria, @PathVariable("casa") String casa, Map<String, Object> model) {
        Long hospedariaId = null;
        Long casaId = null;

        try {
            casaId = Long.parseLong(casa);
            hospedariaId = Long.parseLong(hospedaria); // não é usado, mas tambem é feita a verificação
        } catch (NumberFormatException e) { // se tivar letra, espaço ou simbolos irá retorna uma pagina de erro.
            return "error/400"; // bad request: https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/400
        }

        model.put("quartos", quartoService.buscarQuartosPorCasa(casaId));

        return "admin/quartos";
    }

    // TODO: get - ver todas as reservas, incluir filtros para a busca
}
