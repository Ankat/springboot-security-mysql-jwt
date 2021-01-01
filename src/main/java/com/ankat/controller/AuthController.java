package com.ankat.controller;

import com.ankat.entity.User;
import com.ankat.model.AuthRequest;
import com.ankat.model.AuthResponse;
import com.ankat.model.CustomUserDetails;
import com.ankat.repositories.UserRepository;
import com.ankat.services.CustomUserDetailsService;
import com.ankat.util.JwtTokenUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/signIn", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public String signInUser(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = null;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsrUsername(), authRequest.getUsrPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            String jwtToken = jwtTokenUtil.generateToken(customUserDetails);
            authResponse = new AuthResponse(200, "SUCCESS", jwtToken);
        } catch (Exception ex) {
            log.error(ex);
            authResponse = new AuthResponse(201, "FAIL", ex.getMessage());
        }
        return authResponse.toString();
    }

    @PostMapping(value = "/signUp")
    public String signUpUser(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse;
        if (customUserDetailsService.existsByUsrUsername(authRequest.getUsrUsername())) {
            authResponse = new AuthResponse(201, "FAIL", "Username is already exist!");
        }
        User user = new User();
        user.setUsrUsername(authRequest.getUsrUsername());
        user.setUsrPassword(authRequest.getUsrPassword());
        user.setUsrFullName(authRequest.getUsrFullName());
        user.setUsrRole(authRequest.getUsrRole());

        try {
            user = userRepository.save(user);
            authResponse = new AuthResponse(200, "SUCCESS", user);
        } catch (Exception ex) {
            log.error(ex);
            authResponse = new AuthResponse(201, "FAIL", ex.getMessage());
        }

        return authResponse.toString();
    }
}
