package br.com.agropalma.agroquart.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

/**
 * <h1>Hospedaria.java</h1>
 * Representação da tabela "hospedaria"
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Entity
@Table(name = "hospedaria")
public class Hospedaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_hospedaria")
    private String nomeHospedaria;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hospedaria", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Casa> casas;

    public Hospedaria() {
    }

    public Hospedaria(String nomeHospedaria, List<Casa> casas) {
        this.nomeHospedaria = nomeHospedaria;
        this.casas = casas;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeHospedaria() {
        return nomeHospedaria;
    }

    public void setNomeHospedaria(String nomeHospedaria) {
        this.nomeHospedaria = nomeHospedaria;
    }

    public List<Casa> getCasas() {
        return casas;
    }

    public void setCasas(List<Casa> casas) {
        this.casas = casas;
    }

    @Override
    public String toString() {
        return "Hospedaria{" +
                "id=" + id +
                ", nomeHospedaria='" + nomeHospedaria + '\'' +
                '}';
    }
}
