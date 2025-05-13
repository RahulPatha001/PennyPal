package com.example.expense.model;

import com.example.expense.entities.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto extends UserInfo {

    private String userName;

    private String passoword;

    private String email;

    private String phoneNo;
}
