package med.jsrdev.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Anotacion para que spring sepa que es del contexto de seguridad
public class SecurityConfiguration {

    // tipo de objeto para autenticacion stateless
    @Bean // Disponible en mi contexto de spring
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //noinspection removal
        return httpSecurity.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Tipo de algoritmo hashing que usamos para la authentication
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
