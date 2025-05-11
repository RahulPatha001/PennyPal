package com.example.expense.service;

import com.example.expense.entities.UserInfo;
import com.example.expense.model.UserInfoDto;
import com.example.expense.repository.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
     UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found !");
        }
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserIsPresent(UserInfoDto user){
        return userRepository.findByUsername(user.getUserName());
    }

    public Boolean signupUser(UserInfoDto user){
        // add a validation to check if user email and password are strong
        user.setPassoword(passwordEncoder.encode(user.getPassoword()));
        if(Objects.nonNull(checkIfUserIsPresent(user))){
            return false;
        }
        String userId = UUID.randomUUID().toString();
        userRepository.save(new UserInfo(userId, user.getUserName(), user.getPassoword(), new HashSet<>()));
        return true;
    }
}
