package org.blogapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Post {

    @Id
    private String id;
    private String title;
    private String content;
    private String userId;

}
