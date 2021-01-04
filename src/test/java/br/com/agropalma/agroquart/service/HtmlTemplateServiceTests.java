package br.com.agropalma.agroquart.service;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>HtmlTemplateServiceTests.java</h1>
 * Classe de testes para o gerado de template para os emails.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 04/01/2021
 */

@SpringBootTest
public class HtmlTemplateServiceTests {

    @Test
    public void testMustacheCompiler() throws IOException {
        HtmlTemplateService htmlTemplateService = new HtmlTemplateService();

        Map<String, Object> ctx = new HashMap<>();
        ctx.put("nome", "Luiz Filipy");
        ctx.put("ano", "2021");

        String retornado = htmlTemplateService.compilar(ctx, "teste-email");

        String esperado = "Este é um teste de template de email feito por Luiz Filipy em 2021.";

        assertEquals(esperado, retornado, "O valor retornado deve ser igual ao valor esperado");
    }
}
