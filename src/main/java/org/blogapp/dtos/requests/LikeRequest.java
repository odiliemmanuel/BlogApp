package org.blogapp.dtos.requests;

import lombok.Data;

@Data
public class LikeRequest {

    private String postId;
    private String userId;
}
