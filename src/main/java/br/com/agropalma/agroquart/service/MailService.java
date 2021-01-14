package br.com.agropalma.agroquart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>MailSender.java</h1>
 * Classe de serviço para envio de emails.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 04/01/2021
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    private String _assunto;
    private String _conteudo;
    private String _colaborador;
    private String _admin;
    private List<String> _ccAdmins;

    @Value("${agroquart.email.from}")
    private String emailFrom;

    public MailService assunto(String assunto) {
        this._assunto = assunto;
        return this;
    }

    public MailService conteudo(String conteudo) {
        this._conteudo = conteudo;
        return this;
    }

    public MailService colaborador(String colaborador) {
        this._colaborador = colaborador;
        return this;
    }

    public MailService admin(String admin) {
        this._admin = admin;
        return this;
    }

    public MailService ccAdmins(List<String> admins) {
        this._ccAdmins = admins;
        return this;
    }

    public void enviar() throws RuntimeException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(this._colaborador);

        if (this._admin != null) {
            message.setCc(this._admin);
        }

        if (this._ccAdmins != null) {
            message.setBcc(this._ccAdmins.toArray(new String[this._ccAdmins.size()]));
        }

        if (this._admin == null && this._ccAdmins == null) {
            throw new RuntimeException("É necessário enviar cópia da mensagem para algum admin.");
        }

        message.setSubject(this._assunto);
        message.setText(this._conteudo);
        
        emailSender.send(message);
    }
}
