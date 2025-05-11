package com.example.expense.controller;

import com.example.expense.entities.RefreshToken;
import com.example.expense.model.UserInfoDto;
import com.example.expense.response.JwtResponseDto;
import com.example.expense.service.JwtService;
import com.example.expense.service.RefreshTokenService;
import com.example.expense.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto){
        try {
            Boolean isUserSignedUp = userDetailsService.signupUser(userInfoDto);
            if(Boolean.FALSE.equals(isUserSignedUp)){
                return new ResponseEntity<>("User is already signed up !", HttpStatus.BAD_REQUEST);
                }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUserName());
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUserName());
            return new ResponseEntity<>(JwtResponseDto.builder().accessToken(jwtToken).token(refreshToken.getToken())
                    .build(),HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity<>("Error in user service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
