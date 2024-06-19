package dev.ducku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/*AbstractSecurityWebApplicationInitializer: this extended class will automatically call the application context because the ancestor
class implemented WebApplicationInitializer, which mean it will be automatically picked up by web server*/
@Configuration
@EnableWebSecurity(debug = true)
/*@EnableWebSecurity: automatically create a bean name securityFilterChain in WebSecurityConfiguration.class*/


/*Bootstrapping our spring application by DelegatingFilter Proxy, DelegatingFilter Proxy will look up bean named springSecurityFilterChain*/
public class MyAppSecurityInit extends AbstractSecurityWebApplicationInitializer {

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        User.UserBuilder users = User.builder();
        UserDetails user1 = users.username("user1")
                .password(passwordEncoder.encode("123456"))
                .roles("USER", "ADMIN")
                .build();

        UserDetails user2 = users.username("user2")
                .password(passwordEncoder.encode("123456"))
                .roles("VISITOR")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*Spring Security required this bean to authorize the request.*/
    @Bean(name = "mvcHandlerMappingIntrospector")
    HandlerMappingIntrospector handlerInterceptor() {
        return new HandlerMappingIntrospector();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.requestMatchers("/hi", "/hello").hasRole("USER");
            authorizeRequests.requestMatchers("/bye").permitAll();
        });
        http.csrf().disable();
        http.formLogin();
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


}
