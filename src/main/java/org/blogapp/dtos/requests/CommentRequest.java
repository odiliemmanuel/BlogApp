package org.blogapp.dtos.requests;

import lombok.Data;

@Data
public class CommentRequest {

    private String content;
    private String postId;
    private String userId;
}
