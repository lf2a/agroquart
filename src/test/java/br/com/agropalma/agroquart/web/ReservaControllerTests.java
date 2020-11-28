package br.com.agropalma.agroquart.web;

import br.com.agropalma.agroquart.config.AuthenticationSuccessHandlerCustomizado;
import br.com.agropalma.agroquart.config.ProdDbConfig;
import br.com.agropalma.agroquart.service.ReservaService;
import br.com.agropalma.agroquart.service.UsuarioService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

    @Test
    public void testAdicionarCasa() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/reserva")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content("")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
