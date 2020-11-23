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
    private boolean tipo;

    @Column(name = "terceirizado")
    private boolean terceirizado;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "hospedaria_id")
    private Hospedaria hospedaria;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "casa", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Quarto> quartos;

    public Casa() {
    }

    public Casa(long numero, boolean tipo, boolean terceirizado, Hospedaria hospedaria) {
        this.numero = numero;
        this.tipo = tipo;
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

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
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

    public boolean isTipo() {
        return tipo;
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
                ", terceirizado=" + terceirizado +
                ", hospedaria=" + hospedaria +
                '}';
    }
}
