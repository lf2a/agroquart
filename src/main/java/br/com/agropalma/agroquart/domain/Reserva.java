package br.com.agropalma.agroquart.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Reserva.java</h1>
 * Representação da tabela "reserva"
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "gerente_responsavel")
    private String gerenteResponsavel;

    @Column(name = "email")
    private String email;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_termino")
    private LocalDateTime dataTermino;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "autorizada")
    private boolean autorizada;

    @Column(name = "arquivada")
    private boolean arquivada;

    @Column(name = "cargo")
    private String cargo;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @Column(name = "criada_em")
    private LocalDateTime criadaEm;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "sexo")
    private String sexo;

    public static class Builder {

        private Long id;
        private String nomeCompleto;
        private String matricula;
        private String gerenteResponsavel;
        private String email;
        private String empresa;
        private LocalDateTime dataInicio;
        private LocalDateTime dataTermino;
        private String motivo;
        private boolean autorizada;
        private boolean arquivada;
        private String cargo;
        private Quarto quarto;
        private LocalDateTime criadaEm;
        private String tipo;
        private String sexo;

        public Builder() {
            this.id = null;
        }

        public Builder(Long id) {
            this.id = id;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nomeCompleto(String nomeCompleto) {
            this.nomeCompleto = nomeCompleto;
            return this;
        }

        public Builder matricula(String matricula) {
            this.matricula = matricula;
            return this;
        }

        public Builder gerenteResponsavel(String gerenteResponsavel) {
            this.gerenteResponsavel = gerenteResponsavel;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder empresa(String empresa) {
            this.empresa = empresa;
            return this;
        }

        public Builder dataInicio(LocalDateTime dataInicio) {
            this.dataInicio = dataInicio;
            return this;
        }

        public Builder dataTermino(LocalDateTime dataTermino) {
            this.dataTermino = dataTermino;
            return this;
        }

        public Builder motivo(String motivo) {
            this.motivo = motivo;
            return this;
        }

        public Builder autorizada(boolean autorizada) {
            this.autorizada = autorizada;
            return this;
        }

        public Builder arquivar(boolean arquivar) {
            this.arquivada = arquivar;
            return this;
        }

        public Builder cargo(String cargo) {
            this.cargo = cargo;
            return this;
        }

        public Builder quarto(Quarto quarto) {
            this.quarto = quarto;
            return this;
        }

        public Builder criadaEm(LocalDateTime criadaEm) {
            this.criadaEm = criadaEm;
            return this;
        }

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder sexo(String sexo) {
            this.sexo = sexo;
            return this;
        }

        public Reserva build() {
            return new Reserva(this);
        }
    }

    public Reserva() {
    }

    public Reserva(Builder builder) {
        id = builder.id;
        nomeCompleto = builder.nomeCompleto;
        matricula = builder.matricula;
        gerenteResponsavel = builder.gerenteResponsavel;
        email = builder.email;
        empresa = builder.empresa;
        dataInicio = builder.dataInicio;
        dataTermino = builder.dataTermino;
        motivo = builder.motivo;
        autorizada = builder.autorizada;
        arquivada = builder.arquivada;
        cargo = builder.cargo;
        quarto = builder.quarto;
        criadaEm = builder.criadaEm;
        tipo = builder.tipo;
        sexo = builder.sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDateTime dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isAutorizada() {
        return autorizada;
    }

    public void setAutorizada(boolean autorizada) {
        this.autorizada = autorizada;
    }

    public boolean isArquivada() {
        return arquivada;
    }

    public void setArquivar(boolean arquivar) {
        this.arquivada = arquivar;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public Reserva setCriadaEm(LocalDateTime criadaEm) {
        this.criadaEm = criadaEm;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescricaoQuarto() {
        return String.format("Hospedaria: %s - Casa: N°%d - Quarto: N°%d", this.quarto.getCasa().getHospedaria().getNomeHospedaria(),
                this.quarto.getCasa().getNumero(), this.quarto.getId());
    }

    public String getDataInicioFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy HH'h':mm'm'");

        return dataInicio.format(formatter);
    }

    public String getStringDataInicio() {
        return dataInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getStringHoraInicio() {
        return dataInicio.format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public String getDataTerminoFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy HH'h':mm'm'");

        return dataTermino.format(formatter);
    }

    public String getStringDataTermino() {
        return dataTermino.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getStringHoraTermino() {
        return dataTermino.format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public String getCriadaEmFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy HH'h':mm'm'");

        return criadaEm.format(formatter);
    }
}
