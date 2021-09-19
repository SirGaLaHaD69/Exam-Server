package com.exam.controllers.auth;


import com.exam.configs.jwt.JwtUtil;
import com.exam.models.auth.User;
import com.exam.models.jwt.JwtRequest;
import com.exam.models.jwt.JwtResponse;
import com.exam.services.auth.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class JwtAuthenticationController {

    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    public  final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        User user =  (User) jwtUserDetailsService.loadUserByUsername(principal.getName());
        return user;
    }
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateJwtAuthToken(@RequestBody JwtRequest jwtRequest) throws  Exception{

        try {
            this.authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (Exception e){
            logger.warn("BAD CREDENTIALS");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD CREDENTIALS");
        }

        final UserDetails userDetails =  jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    private void authenticate(String username,String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials");
        }

    }

}
