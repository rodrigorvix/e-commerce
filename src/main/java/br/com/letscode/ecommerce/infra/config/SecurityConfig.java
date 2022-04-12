package br.com.letscode.ecommerce.infra.config;

import br.com.letscode.ecommerce.infra.filter.CustomAuthenticationFilter;
import br.com.letscode.ecommerce.infra.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/{user_id}/orders/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/{user_id}/orders/{order_id}/order_items/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/products/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/{user_id}/orders/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/{user_id}/orders/{order_id}/order_items/{product_id}/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/products/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/products/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/{user_id}/orders/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/{user_id}/orders/{order_id}/order_items/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/api/users/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/api/products/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/api/users/{user_id}/orders/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "api/users/{user_id}/orders/{order_id}/order_items/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/actuator/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
