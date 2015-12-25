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

import java.io.Serializable;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

    private EditText cityEditText;
    private TextView errorTextView;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityEditText = (EditText) findViewById(R.id.edit_view_input);
        errorTextView = (TextView) findViewById(R.id.text_view_error);
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage(getString(R.string.loading_message));

        clearAll();

        findViewById(R.id.button_query_pm25).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQueryPM25Click();
            }
        });
    }

    private void onQueryPM25Click() {
        final String city = cityEditText.getText().toString();


        if (!TextUtils.isEmpty(city)) {
            showLoading();
            AirServiceClient.getInstance().requestPM25(city, new Callback<List<PM25>>() {
                @Override
                public void onResponse(Response<List<PM25>> response, Retrofit retrofit) {
                    showSuccessScreen(response);

                }

                @Override
                public void onFailure(Throwable t) {
                    showErrorScreen();
                }
            });
        }
    }

    private void showSuccessScreen(Response<List<PM25>> response) {
        hideLoading();

        if (response != null) {
            Intent intent = new Intent();
            intent.putExtra("data", (Serializable) response.body());
            intent.setClass(MainActivity.this, SecondActivity.class);

            MainActivity.this.startActivity(intent);

        }
    }

    private void showErrorScreen() {
        hideLoading();
        errorTextView.setText(R.string.error_message_query_pm25);
    }

    private void showLoading() {
        loadingDialog.show();
    }

    private void hideLoading() {
        loadingDialog.dismiss();
    }

    private void clearAll() {
        errorTextView.setText("");
    }
}
