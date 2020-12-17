package com.example.p7day03.presenter;

import android.util.Log;

import com.example.p7day03.bean.Javabean;
import com.example.p7day03.call.CallBack;
import com.example.p7day03.contract.MainContract;
import com.example.p7day03.model.ModelImi;

public class IPresenter implements MainContract.IPresenter {
    private final ModelImi modelImi;
    private MainContract.IViews iViews;

    public IPresenter(MainContract.IViews iViews) {
        this.iViews = iViews;
        modelImi = new ModelImi(this);
    }

    @Override
    public void pre() {

       modelImi.getModel("anewslist.json", new CallBack<Javabean>() {

           @Override
           public void onSuccess(Javabean javabean) {
               iViews.getData(javabean);
           }

           @Override
           public void onFail(String error) {
               Log.e("TAG", "网络错误："+error);
           }
       });
    }
}
