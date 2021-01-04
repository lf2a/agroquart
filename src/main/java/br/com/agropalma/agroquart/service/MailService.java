package br.com.agropalma.agroquart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    public void enviar() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nao-responda@agropalma.com.br");
        message.setTo(this._colaborador);

        if (this._admin != null)
            message.setCc(this._admin); // irá ser enviado uma copia da menssagem para o admin logado no sistema

        message.setSubject(this._assunto);
        message.setText(this._conteudo);
        emailSender.send(message);
    }
}
