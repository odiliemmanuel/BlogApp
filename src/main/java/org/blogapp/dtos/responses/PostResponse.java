package org.blogapp.dtos.responses;

import lombok.Data;

@Data
public class PostResponse {

    private String message;
    private int likes;
    private int comments;
    private int views;
}
