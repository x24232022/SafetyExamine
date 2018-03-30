package com.avicsafety.lib.interfaces;

        import java.util.List;

public interface OnNetworkAccessToListListener<T> {
    void onSuccess(List<T> list);
    void onFail(String error);
}
