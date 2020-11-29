package br.com.agropalma.agroquart.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

/**
 * <h1>CasaDaoImpl.java</h1>
 * Representação da tabela "casa"
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Entity
@Table(name = "casa")
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "terceirizado")
    private boolean terceirizado;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "hospedaria_id")
    private Hospedaria hospedaria;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "casa", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Quarto> quartos;

    public Casa() {
    }

    public Casa(Long numero, String tipo, String sexo, boolean terceirizado, Hospedaria hospedaria) {
        this.numero = numero;
        this.tipo = tipo;
        this.sexo = sexo;
        this.terceirizado = terceirizado;
        this.hospedaria = hospedaria;
    }

    public Long getId() {
        return id;
    }

    public Casa setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
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

    public boolean isTerceirizado() {
        return terceirizado;
    }

    public void setTerceirizado(boolean terceirizado) {
        this.terceirizado = terceirizado;
    }

    public Hospedaria getHospedaria() {
        return hospedaria;
    }

    public void setHospedaria(Hospedaria hospedaria) {
        this.hospedaria = hospedaria;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public Casa setQuartos(List<Quarto> quartos) {
        this.quartos = quartos;
        return this;
    }

    @Override
    public String toString() {
        return "Casa{" +
                "id=" + numero +
                ", numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", sexo='" + sexo + '\'' +
                ", terceirizado=" + terceirizado +
                ", hospedaria=" + hospedaria +
                '}';
    }
}
