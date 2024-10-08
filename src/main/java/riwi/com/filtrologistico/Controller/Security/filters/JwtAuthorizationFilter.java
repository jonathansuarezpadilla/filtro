package riwi.com.filtrologistico.Controller.Security.filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import riwi.com.filtrologistico.Controller.Security.JWT.JWTUtils;
import riwi.com.filtrologistico.Service.impl.UserDetailServiceImpl;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private JWTUtils jwtUtils;


    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {



        //recuperando el token
        String tokenHeader = request.getHeader("Authorization");


        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {

            String token = tokenHeader.substring(7);


            if(jwtUtils.isTokenValid(token)){
                String username = jwtUtils.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }
        filterChain.doFilter(request, response); // continuara con el filtro de validacion
    }
}
