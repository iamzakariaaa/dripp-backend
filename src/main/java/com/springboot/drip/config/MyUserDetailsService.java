package com.springboot.drip.config;

import com.springboot.drip.model.User;
import com.springboot.drip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Optional<User> user = userRepository.findByEmail(email);
            user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
            return user.get();
        }

}
