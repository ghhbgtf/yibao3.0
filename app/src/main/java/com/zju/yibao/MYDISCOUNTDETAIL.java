package com.zju.yibao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zju.yibao.bean.MyDiscountDetail;

public class MYDISCOUNTDETAIL extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv_title;
    private TextView tvCourseName;
    private TextView tvDiscountCode;
    private TextView tvDiscountUseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydiscountdetail);

        initView();
        loadData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("优惠劵说明");

        tvCourseName = (TextView) findViewById(R.id.tv_courseName);
        tvDiscountCode = (TextView) findViewById(R.id.tv_discountCode);
        tvDiscountUseInfo = (TextView) findViewById(R.id.tv_discountUseInfo);
    }

    private void loadData() {
        String string = getIntent().getStringExtra("data");
        MyDiscountDetail myDiscountDetail = JSON.parseObject(string, MyDiscountDetail.class);

        tvCourseName.setText(myDiscountDetail.getCourseName() + "的优惠码");
        tvDiscountCode.setText(myDiscountDetail.getDiscountCode());
        tvDiscountUseInfo.setText(myDiscountDetail.getDiscountUseInfo());
    }
}
