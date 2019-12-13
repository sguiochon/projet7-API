package sam.biblio.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@EnableSwagger2
//@EnableEntityLinks
//@Import(SpringDataRestConfiguration.class) Imcompatibilité entre SpringFox et Spring Data Core!il faut attendre la versio n3 de SpringFox
public class Main extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        LOG.info("L'application a demarre.");
    }
}
