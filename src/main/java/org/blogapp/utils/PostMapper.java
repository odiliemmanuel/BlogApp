package org.blogapp.utils;

import org.blogapp.data.models.Post;
import org.blogapp.data.models.User;
import org.blogapp.dtos.requests.CommentRequest;
import org.blogapp.dtos.requests.NewPostRequest;
import org.blogapp.dtos.responses.CommentResponse;
import org.blogapp.dtos.responses.NewPostResponse;
import org.blogapp.dtos.responses.ViewPostResponse;

public class PostMapper {


    public static Post mapNewPostRequestToPost(NewPostRequest newPostRequest){
        Post post = new Post();

        post.setUserId(newPostRequest.getUserId());
        post.setId(newPostRequest.getPostId());
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

    public static ViewPostResponse mapViewPostResponseToPostAndUser(User user, Post post){
        ViewPostResponse viewPostResponse = new ViewPostResponse();

        viewPostResponse.setMessage(user.getUsername() + " just viewed your post: " + post.getTitle());

        return viewPostResponse;
    }


    public static CommentResponse mapCommentResponseToUserAndPost(CommentRequest commentRequest, Post post, User user){
        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setContent(commentRequest.getContent());
        commentResponse.setMessage(user.getUsername() + " commented on your post: " + post.getTitle());

        return commentResponse;

    }

}


