package rs.ac.ftn.uns.upp.scientificcenter;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class ScintificcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScintificcenterApplication.class, args);
    }
}
