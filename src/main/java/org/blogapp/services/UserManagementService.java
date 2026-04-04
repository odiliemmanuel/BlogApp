package org.blogapp.services;

import org.blogapp.data.repositories.PostRepository;
import org.blogapp.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

}
