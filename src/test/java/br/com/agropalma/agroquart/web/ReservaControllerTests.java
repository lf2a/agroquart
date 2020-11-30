package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.config.AuthenticationSuccessHandlerCustomizado;
import br.com.agropalma.agroquart.config.ProdDbConfig;
import br.com.agropalma.agroquart.service.QuartoService;
import br.com.agropalma.agroquart.service.ReservaService;
import br.com.agropalma.agroquart.service.UsuarioService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * <h1>ReservaControllerTests.java</h1>
 * Classe de testes para o controlador de reservas
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 28/11/2020
 */
@RunWith(SpringRunner.class)
@WebMvcTest({ReservaController.class})
public class ReservaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private AuthenticationSuccessHandlerCustomizado successHandler;

    @MockBean
    private ProdDbConfig prodDbConfig;

    @MockBean
    private ReservaService reservaService;

    @MockBean
    private QuartoService quartoService;

    @Test
    public void testAdicionarReserva() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/reserva")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"RESERVA", "EDITAR_RESERVA"})
    public void testAcessoFormEditarReserva() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/reserva/1/editar")) // /reserva/{reserva id}/editar
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"RESERVA", "EDITAR_RESERVA"})
    public void testAlterarReserva() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/reserva/1/editar")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("id=3&nomeCompleto=Carlos+Costa&matricula=65643&gerenteResponsavel=Leticia+Santos&email=carlos%40email.com&cargo=Engenheiro+civil&dataInicio=2020-11-29&horaInicio=10%3A00&dataTermino=2020-11-30&horaTermino=10%3A00&motivo=Fazer+vistoria+nos+computadores")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"RESERVA", "EXCLUIR_RESERVA"})
    public void testExcluirReserva() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/reserva/1/excluir?filtro=reservas")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"RESERVA", "EDITAR_RESERVA"})
    public void testArquivarReserva() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/reserva/1/arquivar?filtro=reservas")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"RESERVA", "EDITAR_RESERVA"})
    public void testAutorizarReserva() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/reserva/1/autorizar?filtro=reservas")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"RESERVA", "EDITAR_RESERVA"})
    public void testAcessoEscolherQuartoDaReserva() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/reserva/1/quarto"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"RESERVA", "EDITAR_RESERVA"})
    public void testEscolherQuartoDaReserva() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/reserva/1/quarto/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
