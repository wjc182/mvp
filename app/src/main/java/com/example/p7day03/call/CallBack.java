package com.example.p7day03.call;

public interface CallBack<T> {
    void onSuccess(T t);

    void onFail(String error);
}
