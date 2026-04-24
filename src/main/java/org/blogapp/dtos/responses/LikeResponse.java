package org.blogapp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class LikeResponse {

    private String message;
    private boolean isLiked;


}
