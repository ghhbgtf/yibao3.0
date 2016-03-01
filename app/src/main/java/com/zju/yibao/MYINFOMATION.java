package com.zju.yibao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zju.yibao.Interface.GetJson;
import com.zju.yibao.Util.MyJsonLoader;
import com.zju.yibao.bean.MyInformation;

/**
 * Created by hmw on 2016/2/2.
 */
public class MYINFOMATION extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener, GetJson {
    private Toolbar toolbar;
    private TextView title;
    private EditText sex;
    private EditText age;
    private EditText preference;
    private Button update;
    private int choice = 0;
    boolean[] selected = new boolean[]{false, false, false, false, false, false};

    private String string_http = "http://115.28.194.35:8080/ArtEducation/student/queryStuInfo.action?studentId=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinformation);

        initView();
        loadData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edit_userinfo_sex:
                if (hasFocus) {
                    chooseSex();
                }
                break;
            case R.id.edit_userinfo_age:
                if (hasFocus) {
                    chooseAge();
                }
                break;
            case R.id.edit_userinfo_preference:
                if (hasFocus) {
                    choosePreference();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_userinfo_sex:
                chooseSex();
                break;
            case R.id.edit_userinfo_age:
                chooseAge();
                break;
            case R.id.edit_userinfo_preference:
                choosePreference();
                break;
            case R.id.btn_update_userinfo:
                break;
        }
    }

    @Override
    public void getJson(String s) {
        System.out.println(s);
        MyInformation myInformation = JSON.parseObject(s, MyInformation.class);
        sex.setText(myInformation.getGender());
        age.setText(myInformation.getStudentAge()+"");
        preference.setText(myInformation.getPreference());
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        title = (TextView) findViewById(R.id.tv_title);
        title.setText("修改个人信息");

        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sex = (EditText) findViewById(R.id.edit_userinfo_sex);
        sex.setOnFocusChangeListener(this);
        sex.setOnClickListener(this);

        age = (EditText) findViewById(R.id.edit_userinfo_age);
        age.setOnClickListener(this);
        age.setOnFocusChangeListener(this);

        preference = (EditText) findViewById(R.id.edit_userinfo_preference);
        preference.setOnFocusChangeListener(this);
        preference.setOnClickListener(this);

        update = (Button) findViewById(R.id.btn_update_userinfo);
        update.setOnClickListener(this);
    }

    private void loadData() {
        new MyJsonLoader(this).loadJson(string_http);
    }

    public void chooseSex() {
        final String[] sexList = {"男", "女"};
        new AlertDialog.Builder(MYINFOMATION.this)
                .setTitle("选择性别")
                .setSingleChoiceItems(
                        sexList, 0,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                choice = which;
                            }
                        })
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice != -1) {
                            sex.setText(sexList[choice]);
                        }
                    }
                })
                .show();
    }

    private void chooseAge() {
        final NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMinValue(10);
        numberPicker.setMaxValue(80);
        numberPicker.setValue(16);

        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                .setTitle("请选择您的年龄")
                .setView(numberPicker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        age.setText(numberPicker.getValue() + "");
                    }
                })
                .setNegativeButton("取消", null);
        builder.create()
                .show();

    }

    public void choosePreference() {

        DialogInterface.OnMultiChoiceClickListener mutiListener =
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        selected[which] = isChecked;
                    }
                };
        DialogInterface.OnClickListener btnListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String selectedStr = "";
                for (int i = 0; i < selected.length; i++) {
                    if (selected[i] == true) {
                        selectedStr = selectedStr + " " +
                                getResources().getStringArray(R.array.preferenceList)[i];
                    }
                }
                preference.setText(selectedStr);
            }
        };

        new AlertDialog.Builder(this)
                .setTitle("选择偏好(可多选)")
                .setMultiChoiceItems(R.array.preferenceList, selected, mutiListener)
                .setPositiveButton("确定", btnListener)
                .create()
                .show();
    }
}
