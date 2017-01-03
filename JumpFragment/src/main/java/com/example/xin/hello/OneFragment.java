package com.example.xin.hello;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OneFragment extends Fragment implements View.OnClickListener {
    private TextView mTextView;
    private Button mButton;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mTextView.setText(msg.obj + "");
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        mTextView = (TextView) view.findViewById(R.id.textview);
        view.findViewById(R.id.one).setOnClickListener(this);
        view.findViewById(R.id.two).setOnClickListener(this);
        mTextView.setOnClickListener(this);
        return view;
    }

    private void reUI(String str) {
        Message msg = mHandler.obtainMessage();
        msg.obj = str;
        mHandler.sendMessage(msg);
    }

    public void getData() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("http://192.168.56.101:8080/Test/test")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                if (htmlStr != null) {
                    reUI(htmlStr);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview:
                getData();
                break;
            case R.id.one:
                getFragmentManager().
                        beginTransaction().
                        addToBackStack(null).
                        replace(R.id.content, new TwoFragment()).commit();
                break;
            case R.id.two:
                getFragmentManager().
                        beginTransaction().
                        addToBackStack(null).
                        replace(R.id.content, new ThreeFragment()).commit();
                break;

        }
    }
}
