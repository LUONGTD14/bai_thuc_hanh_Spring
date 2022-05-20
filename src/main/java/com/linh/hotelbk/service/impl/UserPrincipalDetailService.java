package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserPrincipalDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!userRepository.existsByEmail(email)){
            log.info("Wrong email !");
            throw new UsernameNotFoundException("User not found !");
        }
        log.info("Authenticated successfully");
        return new UserPrincipal(userRepository.findByEmail(email));
    }
}
