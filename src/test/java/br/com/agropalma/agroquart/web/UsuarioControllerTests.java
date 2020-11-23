package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.config.AuthenticationSuccessHandlerCustomizado;
import br.com.agropalma.agroquart.config.ProdDbConfig;
import br.com.agropalma.agroquart.service.PermissaoService;
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
 * <h1>UsuarioControllerTests.java</h1>
 * Classe de testes para o controlador de usuario
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22/11/2020
 */
@RunWith(SpringRunner.class)
@WebMvcTest({UsuarioController.class})
public class UsuarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private AuthenticationSuccessHandlerCustomizado successHandler;

    @MockBean
    private ProdDbConfig prodDbConfig;

    @MockBean
    private PermissaoService permissaoService;

    @Test
    @WithMockUser(roles = {"USUARIO", "CRIAR_USUARIO"})
    public void testAcessoCriarUsuario() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"USUARIO", "EDITAR_USUARIO"})
    public void testAcessoEditarUsuarioGet() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/usuario/1/editar"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"USUARIO", "EDITAR_USUARIO"})
    public void testAcessoEditarUsuario() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/usuario/1/editar")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = {"USUARIO", "EXCLUIR_USUARIO"})
    public void testAcessoExcluirUsuario() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/usuario/1/excluir")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
