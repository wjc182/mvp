package com.example.p7day03.api;

import com.example.p7day03.bean.Javabean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface MyService {

    @GET
    Observable<ResponseBody> get(@Url String uri);

    @POST
    @FormUrlEncoded
    Observable<ResponseBody> post(@Url String uri, @FieldMap HashMap<String,String> map);
}
