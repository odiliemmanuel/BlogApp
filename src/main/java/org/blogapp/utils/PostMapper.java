package org.blogapp.utils;

import org.blogapp.data.models.Post;
import org.blogapp.dtos.requests.NewPostRequest;
import org.blogapp.dtos.responses.NewPostResponse;

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

}


