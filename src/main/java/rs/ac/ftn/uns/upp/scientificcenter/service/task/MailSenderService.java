package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Date;

public class MailSenderService implements JavaDelegate {
    private static final Logger LOGGER = LogManager.getLogger(MailSenderService.class);

    private static final String EMAIL_ADDRESS = "naucna.centrala28@gmail.com";

    private Expression content;

    private Expression sendTo;

    private Expression subject;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final String sendTo = (String) this.sendTo.getValue(execution);
        final String content = (String) this.content.getValue(execution);
        final String subject = (String) this.subject.getValue(execution);

        LOGGER.info("\nSend email to: {} \ncontent: \n{}", sendTo, content);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, true);
        mail.setTo(EMAIL_ADDRESS);
        mail.setText("Sent to: " + sendTo + "\n\n" + content);
        mail.setSubject(subject);
        mail.setFrom(EMAIL_ADDRESS);
        mail.setSentDate(new Date());

        javaMailSender.send(mimeMessage);

    }
}
