package org.cuentas.core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Microservice
 *
 */
@EntityScan("org.cuentas.model.entity")
@EnableJpaRepositories("org.cuentas.data.repository")
@ComponentScan("org.cuentas.core")
@SpringBootApplication
@EnableCaching
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}