package com.avicsafety.lib.interfaces;

public interface OnNetworkAccessToProgressListener {
    void onProgressChanage(int progress);
    void onFail(String error);
}