package dev.ducku.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*AbstractSecurityWebApplicationInitializer: this extended class will automatically call the application context because the ancestor
class implemented WebApplicationInitializer, which mean it will be automatically picked up by web server*/
@Configuration
@EnableWebSecurity
/*@EnableWebSecurity: automatically create a bean name securityFilterChain in WebSecurityConfiguration.class*/


/*Bootstraping our spring application by DelegatingFilter Proxy*/
public class MyAppSecurityInit extends AbstractSecurityWebApplicationInitializer {
}
