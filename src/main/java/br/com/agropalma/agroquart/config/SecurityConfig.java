package br.com.agropalma.agroquart.config;

import br.com.agropalma.agroquart.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuração de segurança.
 * A classe SecurityConfig define todas configurações de segurança, tais como,
 * definição de rotas restritas, rotas de login, logout, páginas de erro 403
 * criptografia e descriptografia de senhas e definição de esquemas de autenticação.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 21-11-2020
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationSuccessHandlerCustomizado authenticationSuccessHandlerCustomizado;

    @Autowired
    private ProdDbConfig prodDbConfig;

    /**
     * Este método irá definir um authentication provider customizado.
     *
     * @param auth Irá permitir aplicar filtros em cada requisição HTTP
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//        auth.jdbcAuthentication().dataSource(prodDbConfig.dataSource());
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
    }



    /**
     * Este método irá definir restrições e rotas de login, logout e de erro 403.
     *
     * @param http Irá permitir aplicar filtros em cada requisição HTTP
     * @throws Exception Erro.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // definindo as permissões de cada rota
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")

                // definindo a rota de login
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandlerCustomizado) // definindo um middleware customizado
                .permitAll()

                // definindo a rota de logout - /logout
                .and()
                .logout().permitAll();
    }

    /**
     * Este método irá definir o tipo do password encoder - BCrypt.
     *
     * @return Retorna a instancia de BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Este método irá definir um password encoder e um user details service customizado.
     *
     * @return Retorna um objeto com as credenciais do usuário.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

        // configurando o user details service customizado
        auth.setUserDetailsService(usuarioService);

        // configurando o password encoder - bcrypt
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }
}