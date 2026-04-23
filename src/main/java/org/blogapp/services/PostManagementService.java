package org.blogapp.services;

import org.blogapp.data.models.Post;
import org.blogapp.data.models.User;
import org.blogapp.data.repositories.PostRepository;
import org.blogapp.data.repositories.UserRepository;
import org.blogapp.dtos.requests.CommentRequest;
import org.blogapp.dtos.requests.NewPostRequest;
import org.blogapp.dtos.requests.ViewPostRequest;
import org.blogapp.dtos.responses.CommentResponse;
import org.blogapp.dtos.responses.NewPostResponse;
import org.blogapp.dtos.responses.ViewPostResponse;
import org.blogapp.exceptions.Messages;
import org.blogapp.exceptions.PostDoesNotExistException;
import org.blogapp.exceptions.PostWithIdAlreadyExistsException;
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

    private static int views = 0;
    private static int comments = 0;

    public NewPostResponse createNewPost(NewPostRequest newPostRequest){

        if(!userRepository.existsById(newPostRequest.getUserId())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }

        if(postRepository.existsById(newPostRequest.getPostId())){
            throw new PostWithIdAlreadyExistsException(Messages.POST_WITH_ID_ALREADY_EXIST_EXCEPTION);
        }

        else{

            Post post = PostMapper.mapNewPostRequestToPost(newPostRequest);
            postRepository.save(post);
            return PostMapper.mapPostToNewPostResponse(post);
        }


    }

    public ViewPostResponse viewPost(ViewPostRequest viewPostRequest){
        User user = userRepository.findUserById(viewPostRequest.getUserId());
        Post post = postRepository.findPostById(viewPostRequest.getPostId());

        if(!userRepository.existsById(user.getId())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }

        if(!postRepository.existsById(post.getId())){
            throw new PostDoesNotExistException(Messages.POST_DOES_NOT_EXIST_EXCEPTION);
        }

        post.setViews(++views);
        postRepository.save(post);
        return PostMapper.mapViewPostResponseToPostAndUser(user, post);

    }


    public CommentResponse commentOnPost(CommentRequest commentRequest){
        Post post = postRepository.findPostById(commentRequest.getPostId());
        User user = userRepository.findUserById(commentRequest.getUserId());

        if(!userRepository.existsById(user.getId())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }

        if(!postRepository.existsById(post.getId())){
            throw new PostDoesNotExistException(Messages.POST_DOES_NOT_EXIST_EXCEPTION);
        }

        if(post.getViews() > 0){
            return PostMapper.mapCommentResponseToUserAndPost
        }

    }

//    private String content;
//    private String postId;

//    private String content;
//    private String message;


}

