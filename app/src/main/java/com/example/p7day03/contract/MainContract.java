package com.example.p7day03.contract;

import com.example.p7day03.bean.Javabean;
import com.example.p7day03.call.CallBack;

public class MainContract {
    //接口
    public interface IModel{

      <T> void getModel(String url,CallBack<T> callBack);
    }
    public interface IPresenter{
       void pre();
    }
    public interface IViews{

        void getData(Javabean javabean);
    }
}
