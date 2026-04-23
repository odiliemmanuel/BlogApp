package org.blogapp.services;

import org.blogapp.data.models.Post;
import org.blogapp.data.repositories.PostRepository;
import org.blogapp.dtos.requests.NewPostRequest;
import org.blogapp.dtos.requests.ViewPostRequest;
import org.blogapp.dtos.responses.NewPostResponse;
import org.blogapp.dtos.responses.ViewPostResponse;
import org.blogapp.exceptions.Messages;
import org.blogapp.exceptions.PostWithIdAlreadyExistsException;
import org.blogapp.utils.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostManagementService {

    @Autowired
    PostRepository postRepository;

    public NewPostResponse createNewPost(NewPostRequest newPostRequest){

        if(postRepository.existsById(newPostRequest.getPostId())){
            throw new PostWithIdAlreadyExistsException(Messages.POST_WITH_ID_ALREADY_EXIST_EXCEPTION);
        }

        else{

            Post post = PostMapper.mapNewPostRequestToPost(newPostRequest);
            postRepository.save(post);
            return PostMapper.mapPostToNewPostResponse(post);
        }


    }

//    public ViewPostResponse view(ViewPostRequest viewPostRequest){
//
//    }


}
// private String postId;
