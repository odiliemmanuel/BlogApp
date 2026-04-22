package org.blogapp.utils;

import org.blogapp.data.models.User;
import org.blogapp.dtos.requests.UserSignUpRequest;
import org.blogapp.dtos.responses.UserLoginResponse;
import org.blogapp.dtos.responses.UserSignUpResponse;
import org.mindrot.jbcrypt.BCrypt;

public class AuthenticationMapper {


    public static User mapUserSignUpRequestToUser(UserSignUpRequest userSignUpRequest){
        User user = new User();

        user.setFirstName(userSignUpRequest.getFirstName());
        user.setLastName(userSignUpRequest.getLastName());
        user.setPhoneNumber(userSignUpRequest.getPhoneNumber());
        user.setUsername(userSignUpRequest.getUserName());
        user.setEmailAddress(userSignUpRequest.getEmailAddress());
        String hashedPassword = BCrypt.hashpw(userSignUpRequest.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        return user;
    }

    public static UserSignUpResponse mapUserToSignUpResponse(User user){
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();

        userSignUpResponse.setUsername(user.getUsername());
        userSignUpResponse.setPhoneNumber(user.getPhoneNumber());
        userSignUpResponse.setMessage("Welcome to FlashCorp Blog App " + user.getUsername());

        return userSignUpResponse;
    }


    public static UserLoginResponse mapUserLoginResponseToUser(User user){
        UserLoginResponse userLoginResponse = new UserLoginResponse();

        userLoginResponse.setUsername(user.getUsername());
        userLoginResponse.setFirstName(user.getFirstName());
        userLoginResponse.setPhoneNumber(user.getPhoneNumber());
        userLoginResponse.setMessage("Welcome back " + user.getUsername());

        return  userLoginResponse;
    }




}

