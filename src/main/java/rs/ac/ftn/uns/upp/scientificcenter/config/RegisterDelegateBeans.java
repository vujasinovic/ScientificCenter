package rs.ac.ftn.uns.upp.scientificcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.ftn.uns.upp.scientificcenter.service.task.*;

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

    @Bean
    public SetAuthorService setAuthorService() {
        return new SetAuthorService();
    }

    @Bean
    public SetupEditorService setupEditorService() {
        return new SetupEditorService();
    }

    @Bean
    public HandleCommentsService handleCommentsService() { return new HandleCommentsService(); }
}
