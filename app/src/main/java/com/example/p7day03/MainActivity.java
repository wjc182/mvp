package com.example.p7day03;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.p7day03.apapter.Myadafel;
import com.example.p7day03.base.BaseActivity;
import com.example.p7day03.bean.Javabean;
import com.example.p7day03.contract.MainContract;
import com.example.p7day03.presenter.IPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<IPresenter> implements MainContract.IViews {
   private RecyclerView rec;
    private ArrayList<Javabean.NewsDTO> list;

    private Myadafel myadafel;
    private List<Javabean.NewsDTO> news;


    @Override
    protected int getDataId() {
        return R.layout.activity_main;
    }

    @Override
    protected IPresenter getPresenter() {
        return new IPresenter(this);
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
    protected void initdata() {
        IPresenter iPresenter = new IPresenter(this);
        iPresenter.pre();
    }

    @Override
    public void getData(Javabean javabean) {
        news = javabean.getNews();
        list.addAll(news);
        myadafel.notifyDataSetChanged();
    }
}