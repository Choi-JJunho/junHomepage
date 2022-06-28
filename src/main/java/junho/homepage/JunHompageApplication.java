package junho.homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// ignore Spring Security configuration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JunHompageApplication {

    public static void main(String[] args) {
        SpringApplication.run(JunHompageApplication.class, args);
    }

}
