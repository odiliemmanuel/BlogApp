package org.blogapp.dtos.requests;

import lombok.Data;

@Data
public class UserSignUpRequest {

    String firstName;
    String lastName;
    String emailAddress;
    String phoneNumber;
    String password;
    String confirmPassword;

}
