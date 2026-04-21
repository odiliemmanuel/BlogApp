package org.blogapp.services;

import org.blogapp.data.repositories.PostRepository;
import org.blogapp.dtos.requests.NewPostRequest;
import org.blogapp.dtos.responses.NewPostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostManagementService {

    @Autowired
    PostRepository postRepository;

    public NewPostResponse createNewPost(NewPostRequest newPostRequest){

    }
}
