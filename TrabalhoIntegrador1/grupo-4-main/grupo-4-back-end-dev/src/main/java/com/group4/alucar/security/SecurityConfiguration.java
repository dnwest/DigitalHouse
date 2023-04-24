package com.group4.alucar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.group4.alucar.enumeration.RoleType;
import com.group4.alucar.security.jwt.JwtAuthenticationFilter;
import com.group4.alucar.security.jwt.secret.Jwks;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private RSAKey rsaKey;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf(crsf -> crsf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // .requestMatchers(HttpMethod.POST, "/user").permitAll()
                // .requestMatchers(HttpMethod.GET, "/car/**", "/category/**", "/feature/**", "/image/**", "/location/**", "/reservation/**", "/client/**").permitAll()
                // .requestMatchers(HttpMethod.POST, "/reservation", "/reservation/on_date", "/reservation/total_invoice_amount", "/client/**", "/image/**", "/feature/**", "/location/**", "/category/**", "/car/**").permitAll()
                // .requestMatchers(HttpMethod.PUT, "/reservation/**", "/car").permitAll()
                // .requestMatchers(HttpMethod.PATCH, "/reservation/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth", "/client").permitAll()
                .requestMatchers(HttpMethod.POST, "/car").hasAnyRole(RoleType.ADMIN.toString())
                .requestMatchers(HttpMethod.POST, "/reservation", "/reservation/total_invoice_amount").hasAnyRole(RoleType.USER.toString())
                .requestMatchers(HttpMethod.PUT, "/car").hasAnyRole(RoleType.ADMIN.toString())
                .requestMatchers(HttpMethod.PATCH, "/reservation/cancel/**").hasAnyRole(RoleType.USER.toString())
                .requestMatchers(HttpMethod.GET, "/category", "/category/all_in_location" , "/category/available_in_location" , "/location").permitAll()
                .requestMatchers(HttpMethod.GET, "/car/all_in_category_and_location").hasAnyRole(RoleType.ADMIN.toString())
                .requestMatchers(HttpMethod.GET, "/client/registration_data", "/reservation/history").hasAnyRole(RoleType.ADMIN.toString(), RoleType.USER.toString())
                .anyRequest().authenticated())
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .headers(headers -> headers.frameOptions().sameOrigin())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(new JwtAuthenticationFilter(jwtDecoder()), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(encoder());
        authProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvider);
    };

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JWKSource<SecurityContext> jwkSource() {
        rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return ((jwkSelector, securityContext) -> jwkSelector.select(jwkSet));
    }

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @DependsOn("jwkSource")
    @Bean
    JwtDecoder jwtDecoder() throws JOSEException {
        if (rsaKey == null) {
            rsaKey = Jwks.generateRsa();
        }
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }

    @Bean
    public JwtGrantedAuthoritiesConverter authoritiesConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix(""); // Cannot be null
        return converter;
    }
    
    @Bean
    public JwtAuthenticationConverter authenticationConverter(JwtGrantedAuthoritiesConverter authoritiesConverter) {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;
    }
}
