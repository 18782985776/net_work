package com.jobnew.farm.data.exception;

/**
 * Created by wufengqiao on 2017-05-18 13:55
 */
public class DefaultErrorException extends RuntimeException {
    public final int code;

    public DefaultErrorException(String detailMessage) {
        this(404, detailMessage);
    }

    public DefaultErrorException(int error, String detailMessage) {
        super(detailMessage);
        this.code = error;
    }
}
