package com.example.p7day03.model;

import com.example.p7day03.call.CallBack;
import com.example.p7day03.contract.MainContract;
import com.example.p7day03.utils.RetroUtikls;

public class ModelImi implements MainContract.IModel {
   private MainContract.IPresenter presenter;

    public ModelImi(MainContract.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public <T> void getModel(String url, CallBack<T> callBack) {
        //拿到数据
        RetroUtikls.getInstance().get(url,callBack);
    }
}
