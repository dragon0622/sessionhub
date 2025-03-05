package com.sessionhub.sessionhub.dto;

import com.sessionhub.sessionhub.domain.entity.User;
import lombok.Getter;

@Getter
public class AddUserResponseDto {

    private final Long id;
    private final String email;
    private final String nickName;

    public AddUserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
    }
}
