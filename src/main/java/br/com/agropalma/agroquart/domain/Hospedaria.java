package br.com.agropalma.agroquart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Hospedaria(String nomeHospedaria, List<Casa> casas) {
        this.nomeHospedaria = nomeHospedaria;
        this.casas = casas;
    }
}
