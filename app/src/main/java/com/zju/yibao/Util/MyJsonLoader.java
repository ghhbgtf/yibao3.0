package com.zju.yibao.Util;

import android.content.Context;
import android.os.AsyncTask;

import com.zju.yibao.Interface.GetJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Atlas on 16/3/1.
 */
public class MyJsonLoader {

    private Context context;
    public MyJsonLoader(Context context) {
        this.context = context;
    }

    public void loadJson(String string) {
        URL url = null;
        try {
            url = new URL(string);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new AsyncTask<URL, Float, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    URLConnection connection = params[0].openConnection();
                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder sb = new StringBuilder();
                    String temp;
                    while ((temp = bufferedReader.readLine()) != null) {
                        sb.append(temp);
                    }

                    bufferedReader.close();
                    inputStreamReader.close();
                    return sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ((GetJson)context).getJson(s);
            }
        }.execute(url);
    }
}
