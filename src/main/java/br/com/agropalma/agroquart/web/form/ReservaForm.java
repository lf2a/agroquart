package br.com.agropalma.agroquart.web.form;

import br.com.agropalma.agroquart.web.validation.ValidarEmail;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * <h1>ReservaForm.java</h1>
 * Esta classe serve para fazer a validação do formulario para criar uma nova reserva.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 26/11/2020
 */
public class ReservaForm {

    @NotNull(message = "O campo nome completo é obrigatório")
    @Size(min = 1, message = "O campo nome completo é obrigatório")
    private String nomeCompleto;

    @NotNull(message = "A matrícula é obrigatória")
    @Min(value = 1, message = "A martícula deve ser maior que 0")
    @Max(value = 99999999, message = "A matrícula deve ser menor que 1 milhão")
    private String matricula;

    @NotNull(message = "O campo gerente responsável é obrigatório")
    @Size(min = 1, message = "O campo gerente responsável é obrigatório")
    private String gerenteResponsavel;

    @ValidarEmail
    @NotNull(message = "O email é obrigatório")
    @Size(min = 1, message = "O email é obrigatório")
    private String email;

    @NotNull(message = "Informe seu cargo")
    @Size(min = 1, message = "Informe seu cargo")
    private String cargo;

    @NotNull(message = "O campo empresa é obrigatório")
    @Size(min = 1, message = "O campo empresa é obrigatório")
    private String empresa;

    @NotNull(message = "Deve ter data de inicio")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInicio;

    @NotNull(message = "Deve ter data de inicio")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaInicio;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataTermino;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaTermino;


    @NotNull(message = "O motivo da reserva é obrigatório")
    @Size(min = 1, message = "O motivo da reserva é obrigatório")
    @Size(max = 500, message = "O motivo da reserva está limitado a 500 caracteres")
    private String motivo;

    @NotNull(message = "O tipo da reserva é obrigatório")
    private Long tipoReserva;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getGerenteResponsavel() {
        return gerenteResponsavel;
    }

    public void setGerenteResponsavel(String gerenteResponsavel) {
        this.gerenteResponsavel = gerenteResponsavel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public LocalTime getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(LocalTime horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(Long tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public LocalDateTime getLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return LocalDateTime.of(localDate, localTime);
    }

    public boolean isValidDates() {
        var inicio = getLocalDateTime(dataInicio, horaInicio);
        var termino = getLocalDateTime(dataTermino, horaTermino);

        return inicio.isBefore(termino) || inicio.isEqual(termino);
    }

    @Override
    public String toString() {
        return "ReservaForm{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", matricula='" + matricula + '\'' +
                ", gerenteResponsavel='" + gerenteResponsavel + '\'' +
                ", email='" + email + '\'' +
                ", cargo='" + cargo + '\'' +
                ", empresa='" + empresa + '\'' +
                ", inicio=" + getLocalDateTime(dataInicio, horaInicio) +
                ", termino=" + getLocalDateTime(dataTermino, horaTermino) +
                ", isValidDates=" + isValidDates() +
                ", motivo='" + motivo + '\'' +
                ", tipoReserva=" + tipoReserva +
                '}';
    }
}
