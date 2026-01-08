package com.interntrack.application_service.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    private static final Logger logger = LoggerFactory.getLogger(FlywayConfig.class);

    /**
     * Create Flyway bean manually to ensure migrations run.
     * This is needed because Spring Boot 4.0.1 may not auto-configure Flyway correctly.
     */
    @Bean
    public Flyway flyway(DataSource dataSource) {
        logger.info("Creating Flyway bean...");
        FluentConfiguration configuration = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .baselineDescription("Initial baseline")
                .validateOnMigrate(true)
                .cleanDisabled(true)
                .outOfOrder(false);
        
        Flyway flyway = configuration.load();
        logger.info("Running Flyway migrations...");
        var result = flyway.migrate();
        logger.info("Flyway migration completed successfully. Applied {} migration(s).", result.migrationsExecuted);
        return flyway;
    }
}

