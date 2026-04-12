package org.blogapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private String confirmPassword;

}
