package org.blogapp.utils;

import org.blogapp.data.models.*;
import org.blogapp.dtos.requests.CommentRequest;
import org.blogapp.dtos.requests.LikeRequest;
import org.blogapp.dtos.requests.NewPostRequest;
import org.blogapp.dtos.requests.ViewPostRequest;
import org.blogapp.dtos.responses.CommentResponse;
import org.blogapp.dtos.responses.NewPostResponse;
import org.blogapp.dtos.responses.ViewPostResponse;

import java.time.LocalDateTime;

public class PostMapper {


    public static Post mapNewPostRequestToPost(NewPostRequest newPostRequest){
        Post post = new Post();

        post.setUserId(newPostRequest.getUserId());
        post.setTitle(newPostRequest.getTitle());
        post.setContent(newPostRequest.getContent());
        post.setViews(newPostRequest.getViews());
        post.setComments(newPostRequest.getComments());
        post.setLikes(newPostRequest.getLikes());

        return post;
    }


    public static NewPostResponse mapPostToNewPostResponse(Post post){
        NewPostResponse newPostResponse = new NewPostResponse();

        newPostResponse.setMessage("Post successfully created");
        newPostResponse.setComments(post.getComments());
        newPostResponse.setViews(post.getViews());
        newPostResponse.setLikes(post.getLikes());

        return newPostResponse;

    }

    public static View mapViewPostRequestToViews(ViewPostRequest viewPostRequest){
        View view = new View();

        view.setUserId(viewPostRequest.getUserId());
        view.setPostId(viewPostRequest.getPostId());
        view.setViewedAt(String.valueOf(LocalDateTime.now()));

        return view;
    }

    public static ViewPostResponse mapViewPostResponseToPostAndUser(User user, Post post){
        ViewPostResponse viewPostResponse = new ViewPostResponse();

        viewPostResponse.setMessage(user.getUsername() + " just viewed your post: " + post.getTitle());
        viewPostResponse.setViewTime(LocalDateTime.now());

        return viewPostResponse;
    }

    public static Comment mapCommentRequestToComment(CommentRequest commentRequest){
        Comment comment = new Comment();

        comment.setUserId(commentRequest.getUserId());
        comment.setPostId(commentRequest.getPostId());
        comment.setContent(commentRequest.getContent());

        return comment;
    }

    public static CommentResponse mapCommentResponseToUserAndPost(CommentRequest commentRequest, Post post, User user){
        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setContent(commentRequest.getContent());

        commentResponse.setMessage(user.getUsername() + " commented on your post: " + post.getTitle());

        return commentResponse;

    }


    public static Like mapLikeRequestToUserLikes(LikeRequest likeRequest) {
        Like like = new Like();

        like.setPostId(likeRequest.getPostId());
        like.setUserId(likeRequest.getUserId());

        return like;
    }
}


