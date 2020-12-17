package com.example.app1.util;

import android.util.Log;

import com.example.app1.api.MyService;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ImNextWorkUtil  implements ImNextWorkInterface{
    //单例
    private static volatile ImNextWorkUtil imNextWorkUtil;
    private final MyService myService;

    public ImNextWorkUtil() {
        //
        myService = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URlConstance.baseUrl)
                .build()
                .create(MyService.class);

    }
    public static ImNextWorkUtil getInstance(){
        if(imNextWorkUtil==null){
            synchronized (ImNextWorkUtil.class){
                if(imNextWorkUtil==null){
                    imNextWorkUtil=new ImNextWorkUtil();
                }
            }
        }
        return imNextWorkUtil;
    }

    @Override
    public <T> void get(String url, CallBack<T> callBack) {
        myService.get(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();

                            //得到广泛的接口
                            Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                            //得到具体的接口
                            Type[] types = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            //得到具体的接口
                            Type t=types[0];

                            Gson gson = new Gson();

                            T json = gson.fromJson(string, t);

                            callBack.OnSuccess(json);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onFail("网络错误："+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
