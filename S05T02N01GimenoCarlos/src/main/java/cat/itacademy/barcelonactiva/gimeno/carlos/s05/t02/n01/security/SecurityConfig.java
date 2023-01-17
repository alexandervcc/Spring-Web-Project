package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.constants.Constants.SECRET_JWT;

import com.auth0.jwt.algorithms.Algorithm;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.security.filters.AuthenticationFilter;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.security.filters.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final AuthFailHandler authFailHandler;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Politica de seguridad
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Proteccion de rutas
        http.authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/player/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/player").permitAll()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated();

        // FILTERS
        http.addFilter(authenticationFilter(http.getSharedObject(AuthenticationConfiguration.class)));
        http.addFilterBefore(new JWTAuthorizationFilter(jwtAlgorithm()), UsernamePasswordAuthenticationFilter.class);

        // Error Handler

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    Algorithm jwtAlgorithm() {
        return Algorithm.HMAC256(SECRET_JWT.getBytes());
    }

    AuthenticationFilter authenticationFilter(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(
                authenticationManager(authenticationConfiguration),
                jwtAlgorithm());
        authenticationFilter.setAuthenticationFailureHandler(authFailHandler);
        return authenticationFilter;
    }

}
