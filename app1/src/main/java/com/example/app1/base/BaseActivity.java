package com.example.app1.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public  abstract class BaseActivity<T> extends AppCompatActivity {
  private T presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(presenter==null){
            presenter=getPresenter();
        }
        //上下文
        setContentView(getDataId());
        //准备数据
        initData();
        initView();
        //视图
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getDataId();

    protected abstract T getPresenter();
}
