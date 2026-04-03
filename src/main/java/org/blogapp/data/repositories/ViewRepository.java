package org.blogapp.data.repositories;

import org.blogapp.data.models.Post;
import org.blogapp.data.models.View;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends MongoRepository<View, String> {

}
