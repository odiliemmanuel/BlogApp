package org.blogapp.services;

import org.blogapp.data.repositories.UserRepository;
import org.blogapp.dtos.requests.UserSignUpRequest;
import org.blogapp.dtos.responses.UserSignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest){
        return;
    }
}
