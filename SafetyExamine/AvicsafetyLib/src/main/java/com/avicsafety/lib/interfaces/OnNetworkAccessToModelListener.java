package com.avicsafety.lib.interfaces;

/**
 * Created by Administrator on 2017/3/20.
 */

public interface OnNetworkAccessToModelListener<T> {
    void onSuccess(T model);
    void onFail(String error);
}
