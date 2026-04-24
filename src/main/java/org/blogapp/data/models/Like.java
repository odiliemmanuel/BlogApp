package org.blogapp.data.models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@RequiredArgsConstructor
@Document
@Data
public class Like {

    @Id
    private String id;
    @NonNull
    private String userId;
    @NonNull
    private String postId;
    private String message;


}
