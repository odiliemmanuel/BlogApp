package org.blogapp.services;

import org.blogapp.data.models.Comment;
import org.blogapp.data.models.Post;
import org.blogapp.data.models.User;
import org.blogapp.data.repositories.CommentRepository;
import org.blogapp.data.repositories.PostRepository;
import org.blogapp.data.repositories.UserRepository;
import org.blogapp.dtos.requests.CommentRequest;
import org.blogapp.dtos.responses.CommentResponse;
import org.blogapp.exceptions.Messages;
import org.blogapp.exceptions.PostDoesNotExistException;
import org.blogapp.exceptions.UserDoesNotExistException;
import org.blogapp.utils.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentManagementService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;


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


    public List<Comment> getComments(){
        return commentRepository.findAll();
    }


}
