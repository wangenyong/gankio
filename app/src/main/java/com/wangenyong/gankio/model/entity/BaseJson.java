package com.wangenyong.gankio.model.entity;

import java.io.Serializable;

/**
 * Created by wangenyong on 2017/3/3.
 */

public class BaseJson<T> implements Serializable {
    private T results;
    private boolean error;

    public T getResults() {
        return results;
    }

    public boolean isError() {
        return error;
    }

    public boolean isSuccess() {
        return !error;
    }
}
