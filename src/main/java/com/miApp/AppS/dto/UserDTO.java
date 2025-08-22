package com.miApp.AppS.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    private Long userId;

    private String name;

    private String email;

    private String password;

    private String role;

}
