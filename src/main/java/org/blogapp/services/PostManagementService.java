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

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ViewRepository viewRepository;




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



    public ViewPostResponse viewPost(ViewPostRequest viewPostRequest){
        User user = userRepository.findUserById(viewPostRequest.getUserId());
        Post post = postRepository.findPostById(viewPostRequest.getPostId());
        View view = PostMapper.mapViewPostRequestToViews(viewPostRequest);

        viewRepository.save(view);

        if(!userRepository.existsById(user.getId())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }

        if(!postRepository.existsById(post.getId())){
            throw new PostDoesNotExistException(Messages.POST_DOES_NOT_EXIST_EXCEPTION);
        }

        if(!viewRepository.existsById(view.getId())){
            return PostMapper.mapViewPostResponseToPostAndUser(user, post);
        }
        else{

            post.setViews(post.getViews() + 1);
            postRepository.save(post);

            return PostMapper.mapViewPostResponseToPostAndUser(user, post);

        }


    }


    public CommentResponse commentOnPost(CommentRequest commentRequest){
        Post post = postRepository.findPostById(commentRequest.getPostId());
        User user = userRepository.findUserById(commentRequest.getUserId());
        Comment comment = PostMapper.mapCommentRequestToComment(commentRequest);

        commentRepository.save(comment);

        if(!userRepository.existsById(user.getId())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }

        if(!postRepository.existsById(post.getId())){
            throw new PostDoesNotExistException(Messages.POST_DOES_NOT_EXIST_EXCEPTION);
        }



        else{
            post.setViews(post.getViews() + 1);
            post.setComments(post.getComments() + 1);
            postRepository.save(post);

            return PostMapper.mapCommentResponseToUserAndPost(commentRequest, post, user);
        }

    }


    public LikeResponse likePost(LikeRequest likeRequest){
        Post post = postRepository.findPostById(likeRequest.getPostId());
        User user = userRepository.findUserById(likeRequest.getUserId());
        Like like = PostMapper.mapLikeRequestToUserLikes(likeRequest);

        likeRepository.save(like);

        if(!userRepository.existsById(user.getId())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }

        if(!postRepository.existsById(post.getId())){
            throw new PostDoesNotExistException(Messages.POST_DOES_NOT_EXIST_EXCEPTION);
        }

        if(likeRepository.existsById(like.getId())){
            post.setLikes(post.getLikes() - 1);
            postRepository.save(post);
        }

        else{
            post.setLikes(post.getLikes() +1);
            postRepository.save(post);
            return PostMapper.mapUserLikeResponseToPost(post);
        }

        return null;

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

