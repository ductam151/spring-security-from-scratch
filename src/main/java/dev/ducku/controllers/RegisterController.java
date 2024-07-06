package dev.ducku.controllers;

import dev.ducku.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class RegisterController {

    /*private final UserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    public RegisterController(@Qualifier("jdbcUserDetailsManager") UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String register(@ModelAttribute RegistrationDTO registrationDTO) {
        registrationDTO.setUsername("hehe");
        registrationDTO.setPassword("hehe");
        return "register";
    }

    @PostMapping("/process-register")
    @ResponseBody
    public String processRegister(RegistrationDTO registrationDTO) throws IOException {
        String username = registrationDTO.getUsername();
        String password = passwordEncoder.encode(registrationDTO.getPassword());

        UserDetails user = User.withUsername(username).password(password).roles("USER").build();

        userDetailsManager.createUser(user);

        return "User created with username: " + registrationDTO.getUsername() + " 😊😂😘😗";
    }*/
}
