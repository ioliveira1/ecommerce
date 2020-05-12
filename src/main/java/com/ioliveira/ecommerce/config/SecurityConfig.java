package com.ioliveira.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    //Permite o acesso total (CRUD)
    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };

    //Somente leitura
    private static final String[] PUBLIC_MATCHERS_GET = {
            "/produtos/**",
            "/categorias/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Libera o acesso ao H2 quando estiver no profile test
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        http.cors()//Ativa as configuracoes de cors definidas no Bean corsConfigurationSource()
                .and().csrf().disable();//Desabilita a protecao de ataques CSRF. Ataques baseados pelo armazenamento da autenticacao em sessao

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()//So permite o metodo GET para a lista em PUBLIC_MATCHERS_GET
                .antMatchers(PUBLIC_MATCHERS).permitAll()//Permite o acesso a todos os caminhos no vetor PUBLIC_MATCHERS
                .anyRequest().authenticated();// Exige autenticacao para o restante

        //Assegura que o back-end nao criara sessao de usuario
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    //Permite o acceso basico aos endpoints por multiplas fontes. Por padrao, nao sao permitidas
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
