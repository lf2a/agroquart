package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.domain.Reserva;
import br.com.agropalma.agroquart.service.CasaService;
import br.com.agropalma.agroquart.service.HospedariaService;
import br.com.agropalma.agroquart.service.PermissaoService;
import br.com.agropalma.agroquart.service.QuartoService;
import br.com.agropalma.agroquart.service.ReservaService;
import br.com.agropalma.agroquart.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private ReservaService reservaService;

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

    @GetMapping("/reservas")
    @PreAuthorize("hasRole('ADMIN') && hasRole('ROLE_RESERVA')")
    public String reservas(@RequestParam(required = false) String filtro, Map<String, Object> model) {

        Optional<String> filtroOptional = Optional.ofNullable(filtro);

        List<Reserva> reservasList;

        if (filtroOptional.isPresent()) {
            switch (filtro) {
                case "autorizadas":
                    model.put("tipo", "Reservas autorizadas");
                    reservasList = reservaService.buscarReservasAutorizadas(true);

                    model.put("reservas", reservasList);
                    break;

                case "nao-autorizadas":
                    model.put("tipo", "Reservas não autorizadas");
                    reservasList = reservaService.buscarReservasAutorizadas(false);

                    model.put("reservas", reservasList);
                    break;

                case "arquivadas":
                    model.put("tipo", "Reservas arquivadas");
                    reservasList = reservaService.buscarReservasArquivadas(true);

                    model.put("reservas", reservasList);
                    break;

                case "nao-arquivadas":
                    model.put("tipo", "Reservas não arquivadas");
                    reservasList = reservaService.buscarReservasArquivadas(false);

                    model.put("reservas", reservasList);
                    break;

                case "reservas-em-andamento":
                    model.put("tipo", "Reservas em andamento");

                    reservasList = reservaService.buscarReservasEmAndamento();

                    model.put("reservas", reservasList);
                    break;

                default:
                    return "error/400"; // valor da query string "filtro" errado, irá lançar um erro de "bad request" 400.
            }

            return "admin/reservas";
        }

        model.put("tipo", "Selecione um filtro");
        model.put("reservas", null);

        return "admin/reservas";
    }
}
