package dev.ducku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
/*@EnableWebSecurity: automatically create a bean name securityFilterChain in WebSecurityConfiguration.class*/
public class MySecurityConfig {

//    @Bean
//    UserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
//        User.UserBuilder users = User.builder();
//        UserDetails user1 = users.username("user1")
//                .password(passwordEncoder.encode("123456"))
//                .roles("USER", "ADMIN")
//                .build();
//        System.out.println("{bcrypt}$2a$10$zeE54MU7GJVZVxZtuwlZLu1xea5w1g1AuYRx4.e.fLfv3yhjuTG9C".length());
//
//        UserDetails user2 = users.username("user2")
//                .password(passwordEncoder.encode("123456"))
//                .roles("VISITOR")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    DataSource dataSource() {
        var datasource = new DriverManagerDataSource("jdbc:mysql://localhost/security_abhilash", "root", "");
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        return datasource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean
//    @Primary
//    UserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

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
            authorizeRequests.requestMatchers("/hi", "/hello", "/user").hasRole("USER");
            authorizeRequests.requestMatchers("/bye", "/register", "/WEB-INF/views/**", "/process-register").permitAll();
        });

        /*by default, all POST, PUT, PATCH, DELETE is protected, if not disable csrf -> 403 forbidden*/
        http.csrf().disable();
        http.formLogin();
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    InternalResourceViewResolver viewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
    }
}
