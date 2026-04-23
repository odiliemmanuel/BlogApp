package org.blogapp.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewPostResponse {

    private String message;
    private LocalDateTime viewTime;
}
