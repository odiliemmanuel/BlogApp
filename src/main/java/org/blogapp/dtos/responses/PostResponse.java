package org.blogapp.dtos.responses;

import lombok.Data;

@Data
public class PostResponse {

    private String message;
    private String title;
    private String content;
    private int likes;
    private int comments;
    private int views;
}
