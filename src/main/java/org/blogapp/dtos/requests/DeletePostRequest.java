package org.blogapp.dtos.requests;

import lombok.Data;

@Data
public class DeletePostRequest {

    private String userId;
    private String postId;

}
