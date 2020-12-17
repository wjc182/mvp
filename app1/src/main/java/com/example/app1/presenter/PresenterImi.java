package com.example.app1.presenter;

import com.example.app1.bean.Javabean;
import com.example.app1.contract.MainContract;
import com.example.app1.model.ModelImi;
import com.example.app1.util.CallBack;

public class PresenterImi implements MainContract.IPresenter {
    private final ModelImi modelImi;
    //
    private MainContract.IView iView;

    public PresenterImi(MainContract.IView iView) {
        this.iView = iView;
        modelImi = new ModelImi(this);
    }

    @Override
    public void pre() {
        modelImi.getData("anewslist.json", new CallBack<Javabean>() {

            @Override
            public void OnSuccess(Javabean javabean) {
                iView.ok(javabean);
            }

            @Override
            public void onFail(String error) {
                iView.no("网络错误"+error);
            }
        });
    }
}
