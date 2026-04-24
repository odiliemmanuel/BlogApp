package org.blogapp.controllers;


import org.blogapp.data.models.Post;
import org.blogapp.data.repositories.PostRepository;
import org.blogapp.dtos.requests.*;
import org.blogapp.exceptions.PostDoesNotExistException;
import org.blogapp.exceptions.PostWithIdAlreadyExistsException;
import org.blogapp.exceptions.UserDoesNotExistException;
import org.blogapp.services.CommentManagementService;
import org.blogapp.services.LikeManagementService;
import org.blogapp.services.PostManagementService;
import org.blogapp.services.ViewManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("post")
public class PostManagementController {

    @Autowired
    private PostManagementService postManagementService;

    @Autowired
    private CommentManagementService commentManagementService;

    @Autowired
    private LikeManagementService likeManagementService;

    @Autowired
    private ViewManagementService viewManagementService;

    @Autowired
    PostRepository postRepository;


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
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(viewManagementService.viewPost(viewPostRequest));
        }
        catch(UserDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }


    @PostMapping("/comment/post")
    public ResponseEntity<?> commentOnPost(CommentRequest commentRequest){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(commentManagementService.commentOnPost(commentRequest));
        }
        catch(PostDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }


    @PostMapping("/like/post")
    public ResponseEntity<?> likePost(LikeRequest likeRequest){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(likeManagementService.likePost(likeRequest));
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
            return ResponseEntity.status(HttpStatus.FOUND).body(viewManagementService.getViewers());
        }
        catch(Exception error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }

    }


    @GetMapping("/see/likes")
    public ResponseEntity<?> getLikes(){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(likeManagementService.getLikes());
        }
        catch(Exception error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }

    }


    @GetMapping("/see/comments")
    public ResponseEntity<?> getComments(){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(commentManagementService.getComments());
        }
        catch(Exception error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }

    }

    @GetMapping("/posts/{id}")
    public Post viewPost(@PathVariable String id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setViews(post.getViews() + 1);
        return postRepository.save(post);
    }

    @PostMapping("/posts/{id}/comment")
    public Post comment(@PathVariable String id, @RequestBody String text) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setComments(post.getComments() + 1);
        return postRepository.save(post);
    }
}
