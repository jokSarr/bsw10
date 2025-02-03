package uasz.sn.Gestion_Enseignement.Authentification.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] FOR_PERMANENT = {"/Permanant/**"};
    private static final String[] FOR_VACATAIRE = {"/Vacataire/**"};
    private static final String[] FOR_CHEFDEPARTEMENT = {"/ChefDepartment/**"};

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception{
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                    authorizeHttpRequests
                            .requestMatchers("/js/**", "css/**").permitAll()
                            .requestMatchers("/login**", "/logout**").permitAll()
                            .requestMatchers("/h2/**").permitAll()
                            .requestMatchers(FOR_PERMANENT).hasRole("Permanant")
                            .requestMatchers(FOR_VACATAIRE).hasRole("Vacataire")
                            .requestMatchers(FOR_CHEFDEPARTEMENT).hasRole("ChefDepartment")
                            .requestMatchers("/api/**").permitAll()
                            .requestMatchers("/apiDTO/**").permitAll()
                            .anyRequest().authenticated()
        )
        .formLogin((formLogin) ->
                formLogin
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .successForwardUrl("/")
                        .permitAll()
        )
        .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/api/**")))
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/apiDTO/**")));
        return http.build();
    }
}
