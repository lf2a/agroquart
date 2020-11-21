package br.com.agropalma.agroquart.domain;

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
@Table(name = "permissao")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    public Permissao() {
    }

    public Permissao(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permissao)) return false;

        Permissao permissao = (Permissao) o;

        return id.equals(permissao.id) && nome.equals(permissao.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "Permissao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
