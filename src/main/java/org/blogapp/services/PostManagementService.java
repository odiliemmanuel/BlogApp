package org.blogapp.services;

import org.blogapp.data.models.*;
import org.blogapp.data.repositories.*;
import org.blogapp.dtos.requests.*;
import org.blogapp.dtos.responses.*;
import org.blogapp.exceptions.Messages;
import org.blogapp.exceptions.PostDoesNotExistException;
import org.blogapp.exceptions.UserDoesNotExistException;
import org.blogapp.utils.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostManagementService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;




    public NewPostResponse createNewPost(NewPostRequest newPostRequest){

        if(!userRepository.existsById(newPostRequest.getUserId())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }

        else{

            Post post = PostMapper.mapNewPostRequestToPost(newPostRequest);
            postRepository.save(post);
            return PostMapper.mapPostToNewPostResponse(post);
        }


    }




    public DeletePostResponse removePost(DeletePostRequest deletePostRequest){
        User user = userRepository.findUserById(deletePostRequest.getUserId());
        Post post = postRepository.findPostById(deletePostRequest.getPostId());

        if(!userRepository.existsById(user.getId())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }

        if(!postRepository.existsById(post.getId())){
            throw new PostDoesNotExistException(Messages.POST_DOES_NOT_EXIST_EXCEPTION);
        }

        DeletePostResponse deletePostResponse = new DeletePostResponse();
        postRepository.delete(post);

        deletePostResponse.setMessage("Heyy, " + user.getUsername() + " your post has been successfully removed");
        return deletePostResponse;

    }







}

