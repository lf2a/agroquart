package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.config.AuthenticationSuccessHandlerCustomizado;
import br.com.agropalma.agroquart.config.ProdDbConfig;
import br.com.agropalma.agroquart.service.QuartoService;
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
 * <h1>QuartoControllerTests.java</h1>
 * Classe de testes para o controlador do quarto.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 24/11/2020
 */
@RunWith(SpringRunner.class)
@WebMvcTest({QuartoController.class})
public class QuartoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private AuthenticationSuccessHandlerCustomizado successHandler;

    @MockBean
    private ProdDbConfig prodDbConfig;

    @MockBean
    private QuartoService quartoService;

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "CRIAR_HOSPEDARIA"})
    public void testAdicionarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/quarto")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "EDITAR_HOSPEDARIA"})
    public void testAcessoFormEditarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/quarto/1/editar")) // /quarto/{quarto id}/editar
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "EDITAR_HOSPEDARIA"})
    public void testAlterarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/quarto/1/editar") // /quarto/{quarto id}/editar
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "EXCLUIR_HOSPEDARIA"})
    public void testExcluirCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/quarto/1/excluir") // /quarto/{quarto id}/excluir
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
