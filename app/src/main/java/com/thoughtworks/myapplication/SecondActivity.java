package com.thoughtworks.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
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
    private TextView titleTextView;
    private TextView timeTextView;
    private TableLayout detailsTableLayout;

    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pm25TextView = (TextView) findViewById(R.id.text_view_pm25);
        titleTextView = (TextView) findViewById(R.id.text_view_title);
        timeTextView = (TextView) findViewById(R.id.text_view_time);
        detailsTableLayout = (TableLayout) findViewById(R.id.table_layout_details);
        detailsTableLayout.setStretchAllColumns(true);
        List<PM25> data =  (List<PM25>) getIntent().getSerializableExtra("data");
        String area = data.get(0).getArea();
        String time = data.get(0).getTimePoint();
        showTitle(area);
        showTime(time);
        showDetailTable(data);

    }


    private void showDetailTable(List<PM25> data) {
        if (data != null && !data.isEmpty()) {
            for(int row=0; row<data.size(); row++) {
                PM25 pm25 = data.get(row);
                String quality = pm25.getQuality();
                String position = pm25.getPositionName();
                TableRow tableRow = new TableRow(this);

                if(quality !=null && position !=null) {
                    for(int col=0;col<4;col++) {
                        setDetails(tableRow, pm25, col);
                    }
                    detailsTableLayout.addView(tableRow,new TableLayout.LayoutParams(MP, WC));
                }

            }
        }
    }

    private void setDetails(TableRow tableRow, PM25 pm25, int number) {
        TextView tv = new TextView(this);
        if(number == 0) {
            tv.setText(pm25.getPositionName());
        } else if(number == 1) {
            tv.setText(pm25.getAqi());
        } else if(number == 2) {
            tv.setText(pm25.getQuality());
        } else {
            tv.setText(pm25.getPm25()+"μg/m3");
        }
        tv.setBackgroundResource(R.drawable.shapee);
        tv.setGravity(Gravity.CENTER);
        tableRow.addView(tv);
    }


    private void showTime(String time) {
        String formatTime = time.replaceAll("[A-Z]"," ");
        timeTextView.setText("更新时间： "+ formatTime);
    }

    private void showTitle(String area) {
         titleTextView.setText(area+"实时空气指数");
    }


}
