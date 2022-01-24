package com.stackroute.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Component
public class User {
    @Id
    @NotNull
    private String email;

    @NotNull
    @Pattern(regexp="^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$",message="password must 8 characters, 2 UpperCase, 1 Special Character, 2 Numbers & 3 LowerCase")
    private String password;


}
