package com.example.day1text.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.day1text.R;
import com.example.day1text.model.JsenBean;
import com.example.day1text.presenter.MainPersenter;
import com.example.day1text.view.adapter.MlssAdapter;
import com.example.day1text.view.adapter.PzshAdapter;
import com.example.day1text.view.adapter.RxxpAdapter;
import com.example.day1text.view.interfaces.IMainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private RecyclerView rxxp;
    private RecyclerView mlss;
    private RecyclerView pzsh;
    private MainPersenter mainPersenter;
    private List<JsenBean.ResultBean.RxxpBean.CommodityListBean> commodityList;

    private RxxpAdapter rxxpAdapter;
    private List<JsenBean.ResultBean.MlssBean.CommodityListBeanXX> mlss1;
    private MlssAdapter mlssAdapter;
    private List<JsenBean.ResultBean.PzshBean.CommodityListBeanX> pzss1;
    private PzshAdapter pzshAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initData() {
        mainPersenter = new MainPersenter(this);
    mainPersenter.getData();
       mainPersenter.setView(this);
    }

    private void initView() {
        rxxp = findViewById(R.id.rxxp);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rxxp.setLayoutManager(linearLayoutManager);

        mlss = findViewById(R.id.mlss);
        LinearLayoutManager linearLayout = new LinearLayoutManager(MainActivity.this);
        linearLayout.setOrientation(OrientationHelper.VERTICAL);
        mlss.setLayoutManager(linearLayout);

        pzsh = findViewById(R.id.pzsh);
        LinearLayoutManager linear = new LinearLayoutManager(MainActivity.this);
        linear.setOrientation(OrientationHelper.VERTICAL);
        pzsh.setLayoutManager(linear);




    }

    @Override
    public void onseccess(Object o) {
        JsenBean jsenBean =(JsenBean ) o;

        commodityList = jsenBean.getResult().getRxxp().getCommodityList();
        rxxpAdapter = new RxxpAdapter(MainActivity.this,this.commodityList);
        rxxp.setAdapter(rxxpAdapter);

        mlss1 = jsenBean.getResult().getMlss().getCommodityList();
        mlssAdapter = new MlssAdapter(MainActivity.this,this.mlss1);
        mlss.setAdapter(mlssAdapter);

        pzss1 = jsenBean.getResult().getPzsh().getCommodityList();
        pzshAdapter = new PzshAdapter(MainActivity.this,this.pzss1);
        pzsh.setAdapter(pzshAdapter);

    }

    @Override
    public void Fail(String Err) {

    }
}
