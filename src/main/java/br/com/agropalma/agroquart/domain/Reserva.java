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

import java.util.Date;

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
    private Date dataInicio;

    @Column(name = "data_termino")
    private Date dataTermino;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "autorizada")
    private boolean autorizada;

    @Column(name = "arquivar")
    private boolean arquivar;

    @Column(name = "privado")
    private boolean privado;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @Column(name = "criada_em")
    private Date criadaEm;

    public static class Builder {

        private Long id;
        private String nomeCompleto;
        private String matricula;
        private String gerenteResponsavel;
        private String email;
        private String empresa;
        private Date dataInicio;
        private Date dataTermino;
        private String motivo;
        private boolean autorizada;
        private boolean arquivar;
        private boolean privado;
        private Quarto quarto;
        private Date criadaEm;

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

        public Builder dataInicio(Date dataInicio) {
            this.dataInicio = dataInicio;
            return this;
        }

        public Builder dataTermino(Date dataTermino) {
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
            this.arquivar = arquivar;
            return this;
        }

        public Builder privado(boolean privado) {
            this.privado = privado;
            return this;
        }

        public Builder quarto(Quarto quarto) {
            this.quarto = quarto;
            return this;
        }

        public Builder criadaEm(Date date) {
            this.criadaEm = criadaEm;
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
        arquivar = builder.arquivar;
        privado = builder.privado;
        quarto = builder.quarto;
        criadaEm = builder.criadaEm;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
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

    public boolean isArquivar() {
        return arquivar;
    }

    public void setArquivar(boolean arquivar) {
        this.arquivar = arquivar;
    }

    public boolean isPrivado() {
        return privado;
    }

    public void setPrivado(boolean privado) {
        this.privado = privado;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Date getCriadaEm() {
        return criadaEm;
    }

    public Reserva setCriadaEm(Date criadaEm) {
        this.criadaEm = criadaEm;
        return this;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", matricula='" + matricula + '\'' +
                ", gerenteResponsavel='" + gerenteResponsavel + '\'' +
                ", email='" + email + '\'' +
                ", empresa='" + empresa + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", motivo='" + motivo + '\'' +
                ", autorizada=" + autorizada +
                ", arquivar=" + arquivar +
                ", privado=" + privado +
                ", quarto=" + quarto +
                ", criadaEm=" + criadaEm +
                '}';
    }
}
