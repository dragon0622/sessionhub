package com.sessionhub.sessionhub.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddUserRequestDto {
    private String email;
    private String nickName;
    private String password;

}
