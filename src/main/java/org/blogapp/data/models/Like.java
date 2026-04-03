package org.blogapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Like {

    @Id
    private String id;
    private String userId;
    private String postId;

}
