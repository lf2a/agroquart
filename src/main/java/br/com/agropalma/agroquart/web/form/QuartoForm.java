package br.com.agropalma.agroquart.web.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <h1>QuartoForm.java</h1>
 * Esta classe serve para fazer a validação do formulario para criar um novo quarto.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 24/11/2020
 */
public class QuartoForm {

    @NotNull(message = "O id do quarto é obrigatório")
    @Min(value = 1, message = "O id do quarto deve ser maior que 0")
    @Max(value = 99999999, message = "O id do quarto deve ser menor que 1 milhão")
    private Long id;

    @NotNull(message = "A capacidade é obrigatório")
    @Min(value = 1, message = "A capacidade do quarto deve ser maior que 0")
    @Max(value = 99999999, message = "A capacidade do quarto deve ser menor que 1 milhão")
    private Long capacidade;

    @NotNull(message = "O id da casa é obrigatório")
    @Min(value = 1, message = "O id da casa deve ser maior que 0")
    @Max(value = 99999999, message = "O id da casa deve ser menor que 1 milhão")
    private Long casa;

    @NotNull(message = "O id da hospedaria é obrigatório")
    @Min(value = 1, message = "O id da hospedaria deve ser maior que 0")
    @Max(value = 99999999, message = "O id da hospedaria deve ser menor que 1 milhão")
    private Long hospedaria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Long capacidade) {
        this.capacidade = capacidade;
    }

    public Long getCasa() {
        return casa;
    }

    public void setCasa(Long casa) {
        this.casa = casa;
    }

    public Long getHospedaria() {
        return hospedaria;
    }

    public void setHospedaria(Long hospedaria) {
        this.hospedaria = hospedaria;
    }

    @Override
    public String toString() {
        return "QuartoForm{" +
                "id=" + id +
                ", capacidade=" + capacidade +
                ", casa=" + casa +
                ", hospedaria=" + hospedaria +
                '}';
    }
}
