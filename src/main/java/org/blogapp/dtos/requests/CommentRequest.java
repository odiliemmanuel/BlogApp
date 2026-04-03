package org.blogapp.dtos.requests;

import lombok.Data;

@Data
public class CommentRequest {

    private String content;
    private String userId;
    private String postId;
}
