package com.stackroute.soulmateservice.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Document
public class UserProfile implements Serializable {

    @Id
    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 4, max = 20)
    private String name;

    @NotNull
    @Min(8)
    private String password;

    @NotNull
    @Min(value = 18)
    private int age;

    @NotNull
    private String gender;

    @NotNull
    private String city;


}
