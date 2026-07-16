package com.wdyapplications.prime_access.utils.wdyemail;


import jakarta.mail.*;
import jakarta.mail.Session;
import jakarta.mail.internet.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Properties;

@Service
public class EmailConfig {

    private final Session session;

    public EmailConfig() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.wdyapplications.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        this.session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "noreply@wdyapplications.com",
                        "Dorgeles@2025"
                );
            }
        });
    }
    public Session getSession() {
        return session;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/emails/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

}