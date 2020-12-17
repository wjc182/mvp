package com.example.p7day03.utils;

import com.example.p7day03.call.CallBack;

public interface ImNextWorkInterface {
    //接口model
    <T> void get(String url, CallBack<T> callBack);
}
