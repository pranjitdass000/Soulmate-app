package com.stackroute.soulmateservice.model;


import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
        @Id
        @NotNull
        private String email;

        @NotNull
        private String password;

}
