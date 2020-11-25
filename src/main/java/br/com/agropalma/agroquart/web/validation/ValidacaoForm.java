package br.com.agropalma.agroquart.web.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <h1>ValidacaoForm.java</h1>
 * Contem métodos uteis para verificação de erros.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 25/11/2020
 */
public class ValidacaoForm {

    public static String getUrlErrorMsg(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();

        // busca todos os erros
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        fieldErrorList.forEach(f -> {
            // busca todas as mensagens de erros em uma string separados por um '@' (serve para fazer um split e iterar sobre)
            stringBuilder.append(f.getDefaultMessage() + "@");
        });

        String url = null;

        try {
            // converte a string para uma url com encode utf-8
            url = URLEncoder.encode(stringBuilder.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            stringBuilder.append("Erro ao formatar string@");
        }

        return url;
    }
}
