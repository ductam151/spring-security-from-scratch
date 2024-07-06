package dev.ducku.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*AbstractSecurityWebApplicationInitializer: this extended class will automatically call the application context because the ancestor
class implemented WebApplicationInitializer, which mean it will be automatically picked up by web server*/
@Configuration
/*Bootstrapping our spring application by DelegatingFilter Proxy, DelegatingFilter Proxy will look up bean named springSecurityFilterChain*/
public class MyAppSecurityInit extends AbstractSecurityWebApplicationInitializer {


}
