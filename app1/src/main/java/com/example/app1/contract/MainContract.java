package com.example.app1.contract;

import com.example.app1.bean.Javabean;
import com.example.app1.util.CallBack;

public class MainContract {
    public interface IModel{
       <T> void getData(String url, CallBack<T> callBack);
    }

    public interface IPresenter{
        void pre();
    }

    public interface IView{
        void ok(Javabean javabean);

        void no(String error);

    }
}
