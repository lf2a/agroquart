package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.config.AuthenticationSuccessHandlerCustomizado;
import br.com.agropalma.agroquart.service.CasaService;
import br.com.agropalma.agroquart.service.HospedariaService;
import br.com.agropalma.agroquart.service.PermissaoService;
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
 * <h1>CasaControllerTests.java</h1>
 * Classe de testes para o controlador da casa.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 24/11/2020
 */
@RunWith(SpringRunner.class)
@WebMvcTest({CasaController.class})
public class CasaControllerTests {

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

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "CRIAR_HOSPEDARIA"})
    public void testAdicionarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/casa")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "EDITAR_HOSPEDARIA"})
    public void testAcessoFormEditarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/casa/1/editar")) // /casa/{casa id}/editar
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "EDITAR_HOSPEDARIA"})
    public void testAlterarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/casa/1/editar") // /casa/{casa id}/editar
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"HOSPEDARIA", "EXCLUIR_HOSPEDARIA"})
    public void testExcluirCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/casa/1/excluir") // /casa/{casa id}/excluir
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
