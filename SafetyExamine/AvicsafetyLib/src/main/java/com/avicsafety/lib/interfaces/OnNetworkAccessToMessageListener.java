package com.avicsafety.lib.interfaces;

public interface OnNetworkAccessToMessageListener {
    void onSuccess(String message);
    void onFail(String error);
}