package com.example.demo.starter.dto.user;

import com.example.demo.starter.dto.base.BaseDto;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserDto extends BaseDto {
    private String username;
    private String email;
}
