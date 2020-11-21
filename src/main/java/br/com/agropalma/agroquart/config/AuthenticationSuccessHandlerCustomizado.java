package br.com.agropalma.agroquart.config;

import br.com.agropalma.agroquart.domain.Usuario;
import br.com.agropalma.agroquart.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;


/**
 * Lidando com uma autenticação de usuário bem-sucedida.
 * CustomAuthenticationSuccessHandler implementa AuthenticationSuccessHandler
 * que irá controlar o destino do usário assim que ele for autenticado, fazendo
 * redirecionamentos ou preenchendo informações na sessão.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 21-11-2020
 */
@Component
public class AuthenticationSuccessHandlerCustomizado implements AuthenticationSuccessHandler {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Este método é usado para preencher na sessão as informações do usuário.
     *
     * @param request        Irá fornecer informações sobre a requisição HTTP
     * @param response       Será usado para o redirecionamento do usuário
     * @param authentication Irá fornecer informações do usuário autenticado
     * @throws IOException Erro de E/S.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        // buscando o usuario no bando de dados
        Usuario usuario = usuarioService.buscarUsuarioPorUsername(authentication.getName());

        // usuario inativo
        if (!usuario.isAtivo()) {
            response.sendRedirect(request.getContextPath() + "/login?inativo");
        } else {
            // alterando o ultimo login no banco de dados
            usuario.setUltimoLogin(new Date());
            usuarioService.salvarOuAtualizar(usuario);

            // carregando as informações do usuário na sessão
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            // depois de logado com sucesso o usuário será redirecionado para a pagina home - /
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
