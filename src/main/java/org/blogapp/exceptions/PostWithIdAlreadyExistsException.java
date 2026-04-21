package org.blogapp.exceptions;

public class PostWithIdAlreadyExistsException extends RuntimeException {
    public PostWithIdAlreadyExistsException(String message) {
        super(message);
    }
}
