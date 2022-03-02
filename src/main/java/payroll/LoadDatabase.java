package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Account("Chris's Checking", 10000.00)));
            log.info("Preloading " + repository.save(new Account("Lindsey's Checking", 15000.00)));
        };
    }
}