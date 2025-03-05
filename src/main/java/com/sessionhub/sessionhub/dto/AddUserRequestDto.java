package com.sessionhub.sessionhub.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddUserRequestDto {
    private String email;
    private String nickName;
    private String password;

    public AddUserRequestDto(String email, String nickName, String password) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }
}
