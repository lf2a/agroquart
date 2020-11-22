package br.com.agropalma.agroquart.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

/**
 * <h1>Validador dos campos dos formulários</h1>
 * Aqui será definido os métodos para validação dos campos dos formulários.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22-11-2020
 */
public class ValidacaoCampo implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    /**
     * Iniializa o validador para verificar se as senhas são iguais.
     *
     * @param constraintAnnotation Obter as senhas.
     */
    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    /**
     * Realiza a verificação das duas senhas fornecidas para saber se são iguais.
     *
     * @param value   objeto generico.
     * @param context Fornece dados contextuais e operação ao aplicar um determinado validador de restrição.
     * @return boolean Se as senhas fornecidas forem iguais.
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            final Object firstObj = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
            final Object secondObj = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {
            // faça nada
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }

}