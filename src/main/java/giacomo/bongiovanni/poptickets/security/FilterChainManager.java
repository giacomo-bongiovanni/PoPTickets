package giacomo.bongiovanni.poptickets.security;

import giacomo.bongiovanni.poptickets.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class FilterChainManager {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public FilterChainManager(JwtAuthFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer :: disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/all").permitAll()
                        .requestMatchers("/admin/**").hasRole(Role.ADMIN.toString())
                        .requestMatchers("/superadmin/**").hasRole(Role.SUPERADMIN.toString())
                ).sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer :: disable)
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
