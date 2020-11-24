package br.com.agropalma.agroquart.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <h1>HospedariaForm.java</h1>
 * Esta classe serve para fazer a validação do formulario para criar uma nova hospedaria.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
public class HospedariaForm {

    private Long id;

    @NotNull(message = "O nome da hospedaria é obrigatório")
    @Size(min = 1, message = "O nome da hospedaria é obrigatório")
    private String nomeHospedaria;

    public Long getId() {
        return id;
    }

    public Long setId(Long id) {
        return this.id = id;
    }

    public String getNomeHospedaria() {
        return nomeHospedaria;
    }

    public HospedariaForm setNomeHospedaria(String nomeHospedaria) {
        this.nomeHospedaria = nomeHospedaria;
        return this;
    }

    @Override
    public String toString() {
        return "HospedariaForm{" +
                "nomeHospedaria='" + nomeHospedaria + '\'' +
                '}';
    }
}
