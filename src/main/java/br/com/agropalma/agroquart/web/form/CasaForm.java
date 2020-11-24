package br.com.agropalma.agroquart.web.form;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <h1>CasaForm.java</h1>
 * Esta classe serve para fazer a validação do formulario para criar uma nova casa.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
public class CasaForm {

    @NotNull(message = "O id da casa é obrigatório")
    @Min(value = 1, message = "O id da casa deve ser maior que 0")
    @Max(value = 99999999, message = "O id da casa deve ser menor que 1 milhão")
    private Long id;

    @NotNull(message = "O número do quarto é obrigatório")
    @Min(value = 1, message = "O numero do quarto deve ser maior que zero")
    @Max(value = 99999999, message = "O numero do quarto deve ser menor que 1 milhão")
    private Long numero;

    private boolean tipo;

    private boolean terceirizado;

    @NotNull(message = "O id da hospedaria é obrigatório")
    @Min(value = 1, message = "O id da hospedaria deve ser maior que zero")
    @Max(value = 99999999, message = "O id da hospedaria deve ser menor que 1 milhão")
    private Long hospedaria;

    public Long getId() {
        return id;
    }

    public CasaForm setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getNumero() {
        return numero;
    }

    public CasaForm setNumero(Long numero) {
        this.numero = numero;
        return this;
    }

    public boolean isTipo() {
        return tipo;
    }

    public CasaForm setTipo(boolean tipo) {
        this.tipo = tipo;
        return this;
    }

    public boolean isTerceirizado() {
        return terceirizado;
    }

    public CasaForm setTerceirizado(boolean terceirizado) {
        this.terceirizado = terceirizado;
        return this;
    }

    public Long getHospedaria() {
        return hospedaria;
    }

    public CasaForm setHospedaria(Long hospedaria) {
        this.hospedaria = hospedaria;
        return this;
    }

    @Override
    public String toString() {
        return "CasaForm{" +
                "id=" + id +
                ", numero=" + numero +
                ", tipo=" + tipo +
                ", terceirizado=" + terceirizado +
                ", hospedaria=" + hospedaria +
                '}';
    }
}
