package com.lloufa.gestionstockback.security.config;

import com.lloufa.gestionstockback.Utils.ConstantEnumUtils;
import com.lloufa.gestionstockback.security.service.ApplicationUserDetailsService;
import com.lloufa.gestionstockback.security.service.JwtService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApplicationDetailsService extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    public ApplicationDetailsService(JwtService jwtService, ApplicationUserDetailsService applicationUserDetailsService) {
        this.jwtService = jwtService;
        this.applicationUserDetailsService = applicationUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(ConstantEnumUtils.AUTHORIZATION_HEADER.getValue());

        String userEmail = null;
        String token = null;
        String idEntreprise = null;

        if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            userEmail = this.jwtService.extractUsername(token);
            idEntreprise = this.jwtService.extractIdEntreprise(token);
        }

        if (StringUtils.hasLength(userEmail) && null == SecurityContextHolder.getContext().getAuthentication()) {
            UserDetails userDetails = this.applicationUserDetailsService.loadUserByUsername(userEmail);

            if (this.jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        MDC.put(ConstantEnumUtils.ID_ENTREPRISE.getValue(), idEntreprise);
        filterChain.doFilter(request, response);
    }

}
