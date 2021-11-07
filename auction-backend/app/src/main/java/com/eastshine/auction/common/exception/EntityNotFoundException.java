package com.eastshine.auction.common.exception;

import com.eastshine.auction.common.response.ErrorCode;

public class EntityNotFoundException extends com.eastshine.auction.common.exception.BaseException {

    public EntityNotFoundException() {
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
