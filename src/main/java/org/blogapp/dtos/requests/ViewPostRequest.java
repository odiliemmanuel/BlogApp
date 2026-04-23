package org.blogapp.dtos.requests;

import lombok.Data;

@Data
public class ViewPostRequest {

    private String userId;
    private String postId;
}
