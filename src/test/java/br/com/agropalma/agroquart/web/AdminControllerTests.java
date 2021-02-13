package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.config.AuthenticationSuccessHandlerCustomizado;
import br.com.agropalma.agroquart.service.CasaService;
import br.com.agropalma.agroquart.service.HospedariaService;
import br.com.agropalma.agroquart.service.PermissaoService;
import br.com.agropalma.agroquart.service.QuartoService;
import br.com.agropalma.agroquart.service.ReservaService;
import br.com.agropalma.agroquart.service.UsuarioService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * <h1>AdminControllerTests.java</h1>
 * Classe de testes para o controlador de admin.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 21/11/2020
 */
@RunWith(SpringRunner.class)
@WebMvcTest({AdminController.class})
public class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private AuthenticationSuccessHandlerCustomizado successHandler;

    @MockBean
    private PermissaoService permissaoService;

    @MockBean
    private HospedariaService hospedariaService;

    @MockBean
    private CasaService casaService;

    @MockBean
    private QuartoService quartoService;

    @MockBean
    private ReservaService reservaService;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAcesso() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USUARIO", "ADMIN"})
    public void testAcessoUsuarios() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/admin/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "ADMIN"})
    public void testAcessoHospedarias() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/admin/hospedarias"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "ADMIN"})
    public void testAcessoCasas() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/admin/1/casas"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "ADMIN"})
    public void testAcessoQuartos() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/admin/1/1/quartos"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"RESERVA", "ADMIN"})
    public void testAcessoReservas() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/admin/reservas"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
