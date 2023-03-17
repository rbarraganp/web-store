package com.iesruizgijon.fleamarket.security;

import com.iesruizgijon.fleamarket.model.User;
import com.iesruizgijon.fleamarket.repository.UserRepository;
import com.iesruizgijon.fleamarket.util.constans.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user=   userRepository.findByEmailUser(email)
                .orElseThrow(() -> new UsernameNotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_USER));

        return new UserDetailsImpl(user);

    }
}
