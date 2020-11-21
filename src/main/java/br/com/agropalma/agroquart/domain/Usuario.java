package br.com.agropalma.agroquart.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <h1>TokenRegistro.java</h1>
 * Representação da tabela "usuario".
 *
 * @author Luiz Filipy
 * @version 2.0
 * @since 27-10-2020
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "matricula")
    private Long matricula;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "ultimo_login")
    private Date ultimoLogin;

    @Column(name = "criada_em")
    private Date criadaEm;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_permissoes",
            joinColumns = @JoinColumn(name = "usuario_matricula"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Collection<Permissao> permissoes;

    public Usuario() {
    }


    public static class Builder {

        private Long matricula;
        private String usuario;
        private String nomeCompleto;
        private String empresa;
        private String email;
        private String senha;
        private boolean ativo;
        private Date ultimoLogin;
        private Date criadaEm;
        private Collection<Permissao> permissoes;

        public Builder() {
            this.matricula = null;
        }

        public Builder(Long matricula) {
            this.matricula = matricula;
        }

        public Builder matricula(Long matricula) {
            this.matricula = matricula;
            return this;
        }

        public Builder usuario(String usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder nomeCompleto(String nomeCompleto) {
            this.nomeCompleto = nomeCompleto;
            return this;
        }

        public Builder empresa(String empresa) {
            this.empresa = empresa;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder ativo(boolean ativo) {
            this.ativo = ativo;
            return this;
        }

        public Builder ultimoLogin(Date ultimoLogin) {
            this.ultimoLogin = ultimoLogin;
            return this;
        }

        public Builder criadaEm(Date criadaEm) {
            this.criadaEm = criadaEm;
            return this;
        }

        public Builder permissoes(List<Permissao> permissoes) {
            this.permissoes = permissoes;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }


    public Usuario(Builder builder) {
        matricula = builder.matricula;
        usuario = builder.usuario;
        nomeCompleto = builder.nomeCompleto;
        empresa = builder.empresa;
        email = builder.email;
        senha = builder.senha;
        ativo = builder.ativo;
        ultimoLogin = builder.ultimoLogin;
        criadaEm = builder.criadaEm;
        permissoes = builder.permissoes;
    }

    public Long getMatricula() {
        return matricula;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public Date getCriadaEm() {
        return criadaEm;
    }

    public Collection<Permissao> getPermissoes() {
        return permissoes;
    }

    public String formatarData(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return simpleDateFormat.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario1 = (Usuario) o;
        return ativo == usuario1.ativo &&
                Objects.equals(matricula, usuario1.matricula) &&
                usuario.equals(usuario1.usuario) &&
                nomeCompleto.equals(usuario1.nomeCompleto) &&
                empresa.equals(usuario1.empresa) &&
                email.equals(usuario1.email) &&
                senha.equals(usuario1.senha) &&
                Objects.equals(ultimoLogin, usuario1.ultimoLogin) &&
                criadaEm.equals(usuario1.criadaEm) &&
                Objects.equals(permissoes, usuario1.permissoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, usuario, nomeCompleto, empresa, email, senha, ativo, ultimoLogin, criadaEm, permissoes);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "matricula=" + matricula +
                ", usuario='" + usuario + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", empresa='" + empresa + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", ativo=" + ativo +
                ", ultimoLogin=" + formatarData(ultimoLogin) +
                ", criadaEm=" + formatarData(criadaEm) +
                ", permissoes=" + permissoes +
                '}';
    }
}
