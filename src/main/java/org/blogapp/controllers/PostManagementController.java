package org.blogapp.controllers;


import org.blogapp.dtos.requests.*;
import org.blogapp.exceptions.PostDoesNotExistException;
import org.blogapp.exceptions.PostWithIdAlreadyExistsException;
import org.blogapp.exceptions.UserDoesNotExistException;
import org.blogapp.services.PostManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostManagementController {

    @Autowired
    private PostManagementService postManagementService;


    @PostMapping("/create/new/post")
    public ResponseEntity<?> makePost(NewPostRequest newPostRequest){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(postManagementService.createNewPost(newPostRequest));
        }
        catch(PostWithIdAlreadyExistsException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }


    @PostMapping("/view/post")
    public ResponseEntity<?> viewPost(ViewPostRequest viewPostRequest){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(postManagementService.viewPost(viewPostRequest));
        }
        catch(UserDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }


    @PostMapping("/comment/post")
    public ResponseEntity<?> commentOnPost(CommentRequest commentRequest){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(postManagementService.commentOnPost(commentRequest));
        }
        catch(PostDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }


    @PostMapping("/like/post")
    public ResponseEntity<?> likePost(LikeRequest likeRequest){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(postManagementService.likePost(likeRequest));
        }
        catch(PostDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }



    @PostMapping("/delete/post")
    public ResponseEntity<?> deletePost(DeletePostRequest deletePostRequest){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(postManagementService.removePost(deletePostRequest));
        }
        catch(PostDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }



    @GetMapping("/see/viewers")
    public ResponseEntity<?> getViewers(){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(postManagementService.getViewers());
        }
        catch(Exception error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }

    }


    @GetMapping("/see/likes")
    public ResponseEntity<?> getLikes(){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(postManagementService.getLikes());
        }
        catch(Exception error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }

    }


    @GetMapping("/see/comments")
    public ResponseEntity<?> getComments(){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(postManagementService.getComments());
        }
        catch(Exception error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }

    }
}
