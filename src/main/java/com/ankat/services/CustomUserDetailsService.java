package com.ankat.services;

import com.ankat.model.CustomUserDetails;
import com.ankat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    @Qualifier(value = "userRepository")
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usrUsername) throws UsernameNotFoundException {
        return userRepository.findByUsrUsername(usrUsername).map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Not Found " + usrUsername));
    }

    public Boolean existsByUsrUsername(String usrUsername){
        return userRepository.findByUsrUsername(usrUsername).isPresent();
    }
}
