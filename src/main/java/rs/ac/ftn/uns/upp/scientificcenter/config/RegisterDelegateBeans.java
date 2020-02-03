package rs.ac.ftn.uns.upp.scientificcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.ftn.uns.upp.scientificcenter.service.task.MailSenderService;
import rs.ac.ftn.uns.upp.scientificcenter.service.task.PersistMagazineService;
import rs.ac.ftn.uns.upp.scientificcenter.service.task.UserRegistrationService;

@Configuration
public class RegisterDelegateBeans {

    @Bean
    public PersistMagazineService persistMagazineService() {
        return new PersistMagazineService();
    }

    @Bean
    public MailSenderService mailSenderService() {
        return new MailSenderService();
    }

    @Bean
    public UserRegistrationService userRegistrationService() {
        return new UserRegistrationService();
    }
}
