package br.com.agropalma.agroquart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * <h1>Permissao.java</h1>
 * Representação da tabela "permissao".
 *
 * @author Luiz Filipy
 * @version 2.0
 * @since 27-10-2020
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissao")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    public Permissao(String nome) {
        this.nome = nome;
    }
}
