package rs.ac.ftn.uns.upp.scientificcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.ftn.uns.upp.scientificcenter.service.implementation.MailSenderService;
import rs.ac.ftn.uns.upp.scientificcenter.service.implementation.PersistMagazineService;

@Configuration
public class RegisterDelegateBeans {

    @Bean
    public PersistMagazineService persistMagazineService() {
        return new PersistMagazineService();
    }

    @Bean
    public MailSenderService mailSenderService() {return new MailSenderService();}
}