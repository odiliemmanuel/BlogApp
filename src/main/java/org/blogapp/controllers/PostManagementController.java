package org.blogapp.controllers;


import org.blogapp.dtos.requests.CommentRequest;
import org.blogapp.dtos.requests.LikeRequest;
import org.blogapp.dtos.requests.NewPostRequest;
import org.blogapp.dtos.requests.ViewPostRequest;
import org.blogapp.exceptions.PostDoesNotExistException;
import org.blogapp.exceptions.PostWithIdAlreadyExistsException;
import org.blogapp.exceptions.UserDoesNotExistException;
import org.blogapp.services.PostManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
