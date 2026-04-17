package org.blogapp.controllers;

import org.blogapp.dtos.requests.UserSignUpRequest;
import org.blogapp.exceptions.UserAlreadyExistsException;
import org.blogapp.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    public AuthenticationService authenticationService;


    public ResponseEntity<?> signUp(UserSignUpRequest userSignUpRequest){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(authenticationService.signUp(userSignUpRequest));
        }
        catch(UserAlreadyExistsException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }

    }
}
