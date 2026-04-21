package org.blogapp.dtos.requests;

import lombok.Data;

@Data
public class NewPostRequest {

    private String title;
    private String content;
    private String postId;
    private int comments = 0;
    private int likes = 0;
    private int views = 0;

}
