package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Date;

public class MailSenderService implements JavaDelegate {
    private static final Logger LOGGER = LogManager.getLogger(MailSenderService.class);

    private Expression content;

    private Expression sendTo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("\nSend email to: {} \ncontent: \n{}", sendTo.getValue(execution), content.getValue(execution));

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
