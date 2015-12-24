package com.thoughtworks.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thoughtworks.myapplication.domain.PM25;
import com.thoughtworks.myapplication.service.AirServiceClient;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SecondActivity extends AppCompatActivity{

    private EditText cityEditText;
    private TextView pm25TextView;
    private ProgressDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pm25TextView = (TextView) findViewById(R.id.text_view_pm25);
        populate();


    }



    private void clearData() {
           pm25TextView.setText("");
    }

    private void populate() {
    //private void populate(List<PM25> data) {
        clearData();
        List<PM25> data =  (List<PM25>) getIntent().getSerializableExtra("data");
        if (data != null && !data.isEmpty()) {
            for(int i=0; i<data.size(); i++) {
                PM25 pm25 = data.get(i);
                if(pm25.getPositionName() !=null ) {
                    pm25TextView.append(pm25.getPositionName() + ": " + pm25.getQuality() + "\r\n");
                }
            }
        }
    }
}
