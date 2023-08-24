package com.codestates.server.exception;

import lombok.Getter;

public enum ExceptionCode {
    USER_NOT_FOUND(404, "User not found"),
    //User not in database.
    USER_EXISTS(409, "User exists"),
//    EMAIL_EXISTS(409, "Email exists"),
    PASSWORD_NOT_MATCH(404, "Password does not match"),
//    NICKNAME_EXISTS(409, "Nickname exists"),
    QUESTION_NOT_FOUND(404, "Question not found"),
//    QUESTION_AUTHOR_NOT_MATCH(404, "The author of the question does not match"),
    ANSWER_NOT_FOUND(404,"Answer not found"),
//    ANSWER_AUTHOR_NOT_MATCH(404,"The author of the answer does not match");
    UNAUTHORIZED_USER(403, "Unauthorized user");
    //unauthorized_user

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
