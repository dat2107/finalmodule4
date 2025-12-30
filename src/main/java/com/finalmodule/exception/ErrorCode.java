package com.finalmodule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // USER ERRORS
    USER_NOT_FOUND("USER_001", "error.user.not_found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("USER_002", "error.user.already_exists", HttpStatus.BAD_REQUEST),
    USERNAME_TAKEN("USER_003", "error.username.taken", HttpStatus.BAD_REQUEST),
    EMAIL_TAKEN("USER_004", "error.email.taken", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD("USER_005", "error.password.invalid", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED("AUTH_006", "error.auth.token_expired", HttpStatus.BAD_REQUEST),
    USER_ALREADY_VERIFIED("USER_007", "error.user.already_verified", HttpStatus.BAD_REQUEST),
    USER_NOT_VERIFIED("USER_008", "error.user.not_verified", HttpStatus.BAD_REQUEST),
    USER_NOT_ACTIVE("USER_009", "error.user.not_active", HttpStatus.BAD_REQUEST),
    INVALID_ROLE("USER_0010", "error.role.invalid", HttpStatus.BAD_REQUEST),

    // AUTH
    UNAUTHORIZED("AUTH_001", "error.auth.unauthorized", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("AUTH_002", "error.auth.forbidden", HttpStatus.FORBIDDEN),
    INVALID_TOKEN("AUTH_003", "error.auth.invalid_token", HttpStatus.UNAUTHORIZED),

    // CATEGORY
    CATEGORY_NOT_FOUND("CAT_001", "error.category.not_found", HttpStatus.NOT_FOUND),

    //PRODUCT
    PRODUCT_NOT_FOUND("PRODUCT_001", "error.product.not_found", HttpStatus.NOT_FOUND),
    // VALIDATION
    VALIDATION_ERROR("VALID_001", "error.validation.failed", HttpStatus.BAD_REQUEST),

    // SERVER
    EMAIL_SEND_FAILED("MAIL_001", "error.mail.send_failed", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_ERROR("SERVER_001", "error.server.internal", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String messageKey;
    private final HttpStatus status;

    ErrorCode(String code, String messageKey, HttpStatus status) {
        this.code = code;
        this.messageKey = messageKey;
        this.status = status;
    }
}
