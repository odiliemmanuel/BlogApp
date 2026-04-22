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
import org.blogapp.utils.AuthenticationMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest){
        User user = AuthenticationMapper.mapUserSignUpRequestToUser(userSignUpRequest);
        if(userRepository.findByEmail(user.getEmailAddress()) != null){
            throw new UserAlreadyExistsException(Messages.USER_ALREADY_EXISTS_EXCEPTION);
        }
        else{
            userRepository.save(user);
            return AuthenticationMapper.mapUserToSignUpResponse(user);
        }
    }


    public UserLoginResponse logIn(UserLoginRequest userLoginRequest){

        if(!userRepository.existsByEmail(userLoginRequest.getEmailAddress())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }
        User foundUser = userRepository.findByEmail(userLoginRequest.getEmailAddress());

        if(!BCrypt.checkpw(userLoginRequest.getPassword(), foundUser.getPassword()))throw new IllegalArgumentException("invalid password");
        return AuthenticationMapper.mapUserLoginResponseToUser(foundUser);

    }




}
