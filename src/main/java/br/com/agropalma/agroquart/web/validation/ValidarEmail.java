package br.com.agropalma.agroquart.web.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * <h1>ValidarEmail.java</h1>
 * Serve para fazer a verificação do email.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22-11-2020
 */
@Constraint(validatedBy = ValidacaoEmail.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidarEmail {
    String message() default "Email inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
