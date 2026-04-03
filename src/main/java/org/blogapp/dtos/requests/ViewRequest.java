package org.blogapp.dtos.requests;

import lombok.Data;

@Data
public class ViewRequest {

    private String userId;
    private String postId;
}
