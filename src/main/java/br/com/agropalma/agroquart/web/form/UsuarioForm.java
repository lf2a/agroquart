package br.com.agropalma.agroquart.web.form;

import br.com.agropalma.agroquart.web.validation.FieldMatch;
import br.com.agropalma.agroquart.web.validation.ValidarEmail;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

/**
 * <h1>UsuarioForm.java</h1>
 * Esta classe serve para fazer a validação do formulario para registrar um novo usuario.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22/11/2020
 */
@FieldMatch.List({@FieldMatch(first = "senha", second = "senha2", message = "As senhas devem ser iguais.")})
public class UsuarioForm {

    @NotNull(message = "Matrícula é obrigatório")
    @Min(value = 1, message = "Matrícula deve ser maior que zero")
    @Max(value = 99999999, message = "Matrícula deve ser menor que 1 milhão")
    private Long matricula;

    @NotNull(message = "Nome de usuário é obrigatório")
    @Size(min = 1, message = "Nome de usuário é obrigatório")
    private String usuario;

    @NotNull(message = "Nome completo é obrigatório")
    @Size(min = 1, message = "Nome completo é obrigatório")
    private String nomeCompleto;

    @NotNull(message = "O nome da empresa é obigatório")
    @Size(min = 1, message = "O nome da empresa é obigatório")
    private String empresa;

    @ValidarEmail
    @NotNull(message = "O email é obrigatório")
    @Size(min = 1, message = "O email é obrigatório")
    private String email;

    private boolean ativo;

    @NotNull(message = "A senha é obrigatória")
    @Size(min = 1, message = "A senha é obrigatória")
    private String senha;

    @NotNull(message = "A confirmação da senha é obrigatória")
    @Size(min = 1, message = "A confirmação da senha é obrigatória")
    private String senha2;

    @NotNull(message = "O usuario deve possuir pelo menos uma permissão")
    @Size(min = 1, message = "O usuario deve possuir pelo menos uma permissão")
    private List<Long> permissoes;

    private Date criadeEm;

    private Date ultimoLogin;

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public List<Long> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Long> permissoes) {
        this.permissoes = permissoes;
    }

    public Date getCriadeEm() {
        return criadeEm;
    }

    public void setCriadeEm(Date criadeEm) {
        this.criadeEm = criadeEm;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    @Override
    public String toString() {
        return "UsuarioForm{" +
                "matricula=" + matricula +
                ", usuario='" + usuario + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", empresa='" + empresa + '\'' +
                ", email='" + email + '\'' +
                ", ativo='" + ativo + '\'' +
                ", senha='" + senha + '\'' +
                ", senha2='" + senha2 + '\'' +
                ", ultimoLogin='" + ultimoLogin + '\'' +
                ", criadaEm='" + criadeEm + '\'' +
                ", permissoes=" + permissoes +
                '}';
    }
}
