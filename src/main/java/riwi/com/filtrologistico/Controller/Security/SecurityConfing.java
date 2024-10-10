package riwi.com.filtrologistico.Controller.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import riwi.com.filtrologistico.Controller.Security.JWT.JWTUtils;
import riwi.com.filtrologistico.Controller.Security.filters.JwtAuthenticationFilter;
import riwi.com.filtrologistico.Controller.Security.filters.JwtAuthorizationFilter;
import riwi.com.filtrologistico.Service.impl.UserDetailServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfing {


    @Autowired
    JWTUtils jwtUtils;


    @Autowired
    UserDetailServiceImpl userDetailsService;

    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);

        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);


        return httpSecurity

                .csrf(csrf->csrf.disable())

                .authorizeHttpRequests(authorize-> {
                    authorize.requestMatchers(HttpMethod.GET,"/","v1/index2").permitAll();
                    authorize.anyRequest().authenticated();
                })


                //Oauth 2.0
                .oauth2Login(Customizer.withDefaults())

                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(jwtAuthenticationFilter)//primer filtro
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class) // segundo filtro


                // login
                .formLogin(form->{
                    form.successHandler(successHandler()).permitAll();
                })

                //
                .sessionManagement(l->{

                    l.sessionCreationPolicy(
                                    SessionCreationPolicy.ALWAYS)
                            .invalidSessionUrl("/login")
                            .maximumSessions(1)
                            .expiredUrl("/login")
                            .sessionRegistry(sessionRegistry());

                    l.sessionFixation().migrateSession();

                })

                .httpBasic(withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true"))
                .build();
    }


    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }


    public AuthenticationSuccessHandler successHandler(){
        return ((request, response, authentication) -> {
            response.sendRedirect("/swagger-ui/index.html");
        });
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();   //NoOpPasswordEncoder.getInstance();
    }


    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity,PasswordEncoder passwordEncoder) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);


        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();


    }

//    public static void main(String[] args){
//        System.out.println(new BCryptPasswordEncoder().encode("jonathanesmicontraseña"));
//    }
}
