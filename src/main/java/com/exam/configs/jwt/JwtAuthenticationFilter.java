package com.exam.configs.jwt;

import com.exam.services.auth.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    public  final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
            final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
            String username = null;
            String jwtToken = null;
            if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
                 jwtToken = requestTokenHeader.substring(7);
                 try {
                     username = jwtUtil.extractUsername(jwtToken);
                 } catch (IllegalArgumentException e) {
                     logger.warn("Unable to get username from JWT");
                 }catch (ExpiredJwtException e){
                        logger.warn("JWT expired !");
                        httpServletResponse.setStatus(401);
                        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token Expired,login again");
                 }
            }
            else{
                System.out.println(requestTokenHeader);
                logger.warn("Invalid Token");
            }
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
                if(jwtUtil.validateToken(jwtToken,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
                else{
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token Expired,login again");
                    logger.warn("User validation failed");
                }
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
