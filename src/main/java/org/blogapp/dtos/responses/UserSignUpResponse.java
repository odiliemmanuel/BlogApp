package org.blogapp.dtos.responses;

import lombok.Data;

@Data
public class UserSignUpResponse {

    String username;
    String phoneNumber;
    String message;
}
