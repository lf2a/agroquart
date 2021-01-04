package br.com.agropalma.agroquart.service;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;

import java.util.Map;

/**
 * <h1>HtmlTemplateService.java</h1>
 * Classe de serviço para gerar o template dos emails.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 04/01/2021
 */
@Service
public class HtmlTemplateService {

    public String compilar(Map<String, Object> context, String template) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(String.format("templates/email/%s.mustache", template));

        StringWriter writer = new StringWriter();
        mustache.execute(writer, context).flush();

        return writer.toString();
    }
}
