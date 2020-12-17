package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.app1.adapter.Myadafel;
import com.example.app1.base.BaseActivity;
import com.example.app1.bean.Javabean;
import com.example.app1.contract.MainContract;
import com.example.app1.presenter.PresenterImi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<PresenterImi> implements MainContract.IView {

  private RecyclerView rec;
    private ArrayList<Javabean.NewsDTO> list;
    private Myadafel myadafel;


    @Override
    protected void initData() {
        PresenterImi presenterImi = new PresenterImi(this);
        presenterImi.pre();
    }

    @Override
    protected void initView() {
        rec=findViewById(R.id.rew_main);
        rec.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myadafel = new Myadafel(this, list);
        rec.setAdapter(myadafel);

    }

    @Override
    protected int getDataId() {
        return R.layout.activity_main;
    }

    @Override
    protected PresenterImi getPresenter() {

        return new PresenterImi(this);
    }

    @Override
    public void ok(Javabean javabean) {
        List<Javabean.NewsDTO> news = javabean.getNews();
        list.addAll(news);
        myadafel.notifyDataSetChanged();
    }

    @Override
    public void no(String error) {

    }
}