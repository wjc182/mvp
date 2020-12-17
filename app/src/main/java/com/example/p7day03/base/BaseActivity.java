package com.example.p7day03.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract  class BaseActivity<T> extends AppCompatActivity {
   private T presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //判断
         if(presenter==null){
             presenter=getPresenter();
         }
         //上下文
        setContentView(getDataId());
        initdata();
        initView();
    }

    protected abstract int getDataId();

    protected abstract T getPresenter();

    protected abstract void initView();

    protected abstract void initdata();
}
