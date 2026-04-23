package org.blogapp.dtos.requests;

import lombok.Data;

@Data
public class NewPostRequest {

    private String userId;
    private String title;
    private String content;
    private int comments = 0;
    private int likes = 0;
    private int views = 0;

}
