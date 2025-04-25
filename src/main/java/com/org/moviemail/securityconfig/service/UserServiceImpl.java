package com.org.moviemail.securityconfig.service;

import com.org.moviemail.securityconfig.dto.UserProfileResponseDto;
import com.org.moviemail.securityconfig.entity.User;
import com.org.moviemail.securityconfig.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public
class UserServiceImpl implements UserDetailsService,UserService{

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public UserProfileResponseDto getCurrentUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserProfileResponseDto(user.getId(), user.getUsername(), user.getRole().name());
    }
}

