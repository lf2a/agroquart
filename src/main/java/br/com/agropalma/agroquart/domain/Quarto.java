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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

/**
 * <h1>Quarto.java</h1>
 * Representação da tabela "quarto"
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/11/2020
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quarto")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "capacidade")
    private Long capacidade;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "casa_id")
    private Casa casa;

    @Column(name = "reservado")
    private Long reservado;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quarto", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Reserva> reservas;
}
