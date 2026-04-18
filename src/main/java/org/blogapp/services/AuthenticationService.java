package org.blogapp.services;

import org.blogapp.data.models.User;
import org.blogapp.data.repositories.UserRepository;
import org.blogapp.dtos.requests.UserLoginRequest;
import org.blogapp.dtos.requests.UserSignUpRequest;
import org.blogapp.dtos.responses.UserLoginResponse;
import org.blogapp.dtos.responses.UserSignUpResponse;
import org.blogapp.exceptions.Messages;
import org.blogapp.exceptions.UserAlreadyExistsException;
import org.blogapp.exceptions.UserDoesNotExistException;
import org.blogapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest){
        User user = Mapper.mapUserSignUpRequestToUser(userSignUpRequest);
        if(userRepository.findByEmail(user.getEmailAddress()) != null){
            throw new UserAlreadyExistsException(Messages.USER_ALREADY_EXISTS_EXCEPTION);
        }
        else{
            userRepository.save(user);
            return Mapper.mapUserToSignUpResponse(user);
        }
    }


    public UserLoginResponse logIn(UserLoginRequest userLoginRequest){
        User user = userRepository.findByEmail(userLoginRequest.getEmailAddress());

        if(!user.getEmailAddress().equals(userLoginRequest.getEmailAddress()) && !user.getPassword().equals(userLoginRequest.getPassword())){
            throw new UserDoesNotExistException(Messages.)
        }
    }




}
