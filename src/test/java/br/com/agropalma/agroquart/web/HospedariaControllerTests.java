package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.config.AuthenticationSuccessHandlerCustomizado;
import br.com.agropalma.agroquart.service.HospedariaService;
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
 * <h1>HospedariaControllerTests.java</h1>
 * Classe de testes para o controlador da hospedaria.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 24/11/2020
 */
@RunWith(SpringRunner.class)
@WebMvcTest({HospedariaController.class})
public class HospedariaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private AuthenticationSuccessHandlerCustomizado successHandler;

    @MockBean
    private HospedariaService hospedariaService;

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "CRIAR_HOSPEDARIA"})
    public void testAdicionarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/hospedaria")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "EDITAR_HOSPEDARIA"})
    public void testAcessoFormEditarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hospedaria/1/editar")) // /hospedaria/{hospedaria id}/editar
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "EDITAR_HOSPEDARIA"})
    public void testAlterarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/hospedaria/1/editar") // /hospedaria/{hospedaria id}/editar
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "EXCLUIR_HOSPEDARIA"})
    public void testExcluirCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/hospedaria/1/excluir") // /hospedaria/{hospedaria id}/excluir
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
