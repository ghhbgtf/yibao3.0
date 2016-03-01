package com.zju.yibao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zju.yibao.bean.MyCourses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MYCOURSE extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv_title;
    private ListView listView;
    private List<Map<String, Object>> listItems;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycourses);

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
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("我的课程");

        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = (ListView) findViewById(R.id.list);
    }

    private void loadData() {
        listItems = new ArrayList<>();

        String string = "{\n" +
                "    \"myCourses\": ";
        string += getIntent().getStringExtra("data");
        string += "\n" +
                "}";

        MyCourses myCourses = JSON.parseObject(string, MyCourses.class);

        String[] string_from = {"course_name", "course_teacher", "course_organization", "course_image"};
        int[] int_to = {R.id.tv_course_name, R.id.tv_course_teacher, R.id.tv_course_organization, R.id.img_course};

        for (int i = 0; i < myCourses.getMyCourses().size(); i++) {
            Map<String, Object> item = new HashMap<>();

            item.put(string_from[0], myCourses.getMyCourses().get(i).getCourseName());
            item.put(string_from[1], myCourses.getMyCourses().get(i).getTeacherName());
            item.put(string_from[2], myCourses.getMyCourses().get(i).getOrganization());
            item.put(string_from[3], R.drawable.user);

            listItems.add(item);
        }

        adapter = new SimpleAdapter(
                MYCOURSE.this,
                listItems,
                R.layout.list_item_course,
                string_from,
                int_to);

        listView.setAdapter(adapter);
    }
}
