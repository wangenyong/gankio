package com.wangenyong.gankio.model.entity;

/**
 * Created by wangenyong on 2017/3/3.
 */

public class ApiException extends RuntimeException {
    public ApiException(String detailMessage) {
        super(detailMessage);
    }
}
