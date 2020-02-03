package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Date;

public class MailSenderService implements JavaDelegate {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        /*
        String processInstanceId = execution.getProcessInstanceId();
        String address = (String) execution.getVariable("email");

        System.out.println(address);

        String link = "http://localhost:3000/activate/" + processInstanceId;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, true);
        mail.setTo(address);
        mail.setText("You can confirm your registration by clicking on this link: " + link);
        mail.setSubject("Registration confirmation");
        mail.setFrom("naucna.centrala28@gmail.com");
        mail.setSentDate(new Date());

        javaMailSender.send(mimeMessage);
         */
    }
}
