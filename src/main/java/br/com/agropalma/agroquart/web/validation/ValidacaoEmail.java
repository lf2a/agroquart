package br.com.agropalma.agroquart.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <h1>Validador de email</h1>
 * Aqui será definido os métodos para validação de email.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22-11-2020
 */
public class ValidacaoEmail implements ConstraintValidator<ValidarEmail, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Irá fazer a validação do email através de REGEX.
     * <p>
     * <b>Observção:</b> A validação do email será com base na cadeia de caracteres fornecida
     * pelo usuário. Não será enviado nenhum email a fim de verificar o proprietário do email.
     *
     * @param email   O email fornecido pelo usuário
     * @param context Fornece dados contextuais e operação ao aplicar um determinado validador de restrição.
     * @return boolean Irá retornar um true se o email foi validado com sucesso.
     */
    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        pattern = Pattern.compile(EMAIL_PATTERN);

        if (email == null) {
            return false;
        }

        matcher = pattern.matcher(email);

        return matcher.matches();
    }

}