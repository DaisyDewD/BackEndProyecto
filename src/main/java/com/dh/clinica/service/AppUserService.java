package com.dh.clinica.service;


import com.dh.clinica.repository.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.UsernameNotFoundException;
=======
>>>>>>> 8943bfb32cf38b3fbcc201f1776325fbec48297b
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

<<<<<<< HEAD
    private final com.dh.clinica.repository.impl.UserRepository userRepository;
=======
    private final UserRepository userRepository;
>>>>>>> 8943bfb32cf38b3fbcc201f1776325fbec48297b

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        return userRepository.findByEmail(email).get();
    }
}
