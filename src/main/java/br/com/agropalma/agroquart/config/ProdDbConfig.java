package br.com.agropalma.agroquart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import java.util.Properties;

/**
 * <h1>ProdDbConfig.java</h1>
 * DataSource para conexão usando o banco de dados de produçao.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 21/11/2020
 */
@Configuration
@EnableJpaRepositories(basePackages = {"br.com.agropalma.agroquart.domain"})
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ProdDbConfig {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "application.properties")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(environment.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(environment.getProperty("spring.datasource.password"));
        dataSourceBuilder.password(environment.getProperty("spring.datasource.username"));
        return dataSourceBuilder.build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("br.com.agropalma.agroquart.domain");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("spring.jpa.properties.hibernate.format_sql"));

        em.setJpaProperties(jpaProperties);

        return em;
    }
}
