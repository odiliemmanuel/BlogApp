package org.blogapp.utils;

import org.blogapp.data.models.User;
import org.blogapp.dtos.requests.UserSignUpRequest;

public class Mapper {


    public static User mapUserSignUpRequestToUser(UserSignUpRequest userSignUpRequest){
        User user = new User();
        user.setFirstName(userSignUpRequest.getFirstName());
        user.setLastName(userSignUpRequest.getLastName());
        user.setPhoneNumber(userSignUpRequest.getPhoneNumber());
        user.setUsername(userSignUpRequest.getUserName());
        user.setEmailAddress(userSignUpRequest.getEmailAddress());
        user.setPassword(userSignUpRequest.getPassword());
        return user;
    }
}
