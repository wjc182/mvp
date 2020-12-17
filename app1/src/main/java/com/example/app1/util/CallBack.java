package com.example.app1.util;

public interface CallBack <T>{

    void OnSuccess(T t);

    void onFail(String error);


}
