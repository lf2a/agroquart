package br.com.agropalma.agroquart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * <h1>H2Config.java</h1>
 * Classe de configuração do H2 para execução dos testes.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 20/11/2020
 */
@Configuration
@EnableJpaRepositories(basePackages = "br.com.agropalma.agroquart.domain")
@PropertySource("classpath:test.properties")
@EnableTransactionManagement
public class H2Config {

    @Autowired
    private Environment env;

    @Bean("h2DataSource")
    @Profile("test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));

        return dataSource;
    }
}
