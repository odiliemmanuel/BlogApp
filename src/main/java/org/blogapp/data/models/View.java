package org.blogapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document
@Data
public class View {

    @Id
    private String id;
    private String userId;
    private String postId;
    private String viewedAt = String.valueOf(LocalDateTime.now());
    private String message;
}
