package org.blogapp.data.repositories;

import org.blogapp.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailAddress(String emailAddress);

    User findUserById(String id);
    boolean existsByEmailAddress(String emailAddress);
}
