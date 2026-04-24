package org.blogapp.services;

import org.blogapp.data.models.Post;
import org.blogapp.data.models.User;
import org.blogapp.data.models.View;
import org.blogapp.data.repositories.PostRepository;
import org.blogapp.data.repositories.UserRepository;
import org.blogapp.data.repositories.ViewRepository;
import org.blogapp.dtos.requests.ViewPostRequest;
import org.blogapp.dtos.responses.ViewPostResponse;
import org.blogapp.exceptions.Messages;
import org.blogapp.exceptions.PostDoesNotExistException;
import org.blogapp.exceptions.UserDoesNotExistException;
import org.blogapp.utils.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ViewManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ViewRepository viewRepository;

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


    public List<View> getViewers(){
        return viewRepository.findAll();
    }


}
