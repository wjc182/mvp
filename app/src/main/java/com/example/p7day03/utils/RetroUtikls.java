package com.example.p7day03.utils;

import com.example.p7day03.api.MyService;
import com.example.p7day03.bean.Javabean;
import com.example.p7day03.call.CallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetroUtikls implements ImNextWorkInterface{
    private static RetroUtikls retroUtikls;
    private final MyService myService;



    //构造方法
    public RetroUtikls(){

        myService = new Retrofit.Builder()
                .baseUrl(URLConstant.News)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MyService.class);
    }
    //单例
    public static RetroUtikls getInstance(){
        if(retroUtikls==null){
            synchronized (RetroUtikls.class){
                if(retroUtikls==null){
                    retroUtikls=new RetroUtikls();
                }
            }
        }
        return retroUtikls;
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

                            //参数化类型
                            Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                            //强转为具体的参数类型
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();

                            Type t=actualTypeArguments[0];

                            Gson gson=new Gson();

                            T result=gson.fromJson(string,t);

                            callBack.onSuccess(result);

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
