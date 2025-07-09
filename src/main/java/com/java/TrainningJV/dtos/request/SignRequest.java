package com.java.TrainningJV.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignRequest implements Serializable {
    @NotNull(message = "email must be not null")
    @Email
    private String email;

    @NotNull(message = "password must be not null" )
    private String password;
}
