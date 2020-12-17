package com.example.app1.model;

import com.example.app1.contract.MainContract;
import com.example.app1.util.CallBack;
import com.example.app1.util.ImNextWorkUtil;

public class ModelImi implements MainContract.IModel {
     private MainContract.IPresenter presenter;

    public ModelImi(MainContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public <T> void getData(String url, CallBack<T> callBack) {
        ImNextWorkUtil.getInstance().get(url,callBack);
    }
}
