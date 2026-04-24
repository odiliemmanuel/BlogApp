package org.blogapp.services;

import org.blogapp.data.models.Like;
import org.blogapp.data.models.Post;
import org.blogapp.data.models.User;
import org.blogapp.data.repositories.LikeRepository;
import org.blogapp.data.repositories.PostRepository;
import org.blogapp.data.repositories.UserRepository;
import org.blogapp.dtos.requests.LikeRequest;
import org.blogapp.dtos.responses.LikeResponse;
import org.blogapp.exceptions.Messages;
import org.blogapp.exceptions.PostDoesNotExistException;
import org.blogapp.exceptions.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeManagementService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    LikeRepository likeRepository;


    public LikeResponse likePost(LikeRequest likeRequest) {
        Post post = postRepository.findPostById(likeRequest.getPostId());
        User user = userRepository.findUserById(likeRequest.getUserId());
        Like like = new Like(likeRequest.getUserId(), likeRequest.getPostId());
//
        likeRepository.save(like);

        if (!postRepository.existsById(post.getId())) {
            throw new PostDoesNotExistException(Messages.POST_DOES_NOT_EXIST_EXCEPTION);
        }

        if(!userRepository.existsById(user.getId())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }


        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(likeRequest.getUserId(), likeRequest.getPostId());


        if (existingLike.isPresent()) {

            likeRepository.delete(existingLike.get());
            return new LikeResponse("Unliked", false);
        }
        else {

            like = new Like(likeRequest.getUserId(), likeRequest.getPostId());
            likeRepository.save(like);
            return new LikeResponse("Liked", true);
        }

//        if (likeRepository.existsById(like.getId())) {
//            post.setLikes(post.getLikes() - 1);
//            postRepository.save(post);
//        } else {
//            post.setLikes(post.getLikes() + 1);
//            postRepository.save(post);
//            return PostMapper.mapUserLikeResponseToPost(post);
//        }
//
//        return null;

    }


    public List<Like> getLikes(){
        return likeRepository.findAll();
    }

}
