package dev.ducku.services;

import dev.ducku.dto.StudentDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final JdbcTemplate jdbcTemplate;

    public CustomUserDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Load user 👤
        String sql = "SELECT * FROM student WHERE username = ?";
        StudentDTO studentDTO = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StudentDTO.class), username).get(0);
        if (studentDTO == null) {
            throw new UsernameNotFoundException("User doesn't exist: " + username);
        }
        UserDetails user = User.withUsername(studentDTO.getUsername()).password(studentDTO.getPassword()).authorities(studentDTO.getRole()).build();
        return user;
    }
}
