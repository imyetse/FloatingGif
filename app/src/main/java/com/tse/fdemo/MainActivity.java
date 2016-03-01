package com.tse.fdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nineoldandroids.animation.AnimatorSet;
import com.tse.fdemo.controller.adapter.LVAdapter;
import com.tse.fdemo.service.testService;
import com.tse.fdemo.widget.AnimatorButton;
import com.tse.fdemo.widget.ListViewForInter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListViewForInter mlistView;
    private AnimatorButton aButton;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private Messenger mService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void sayHello() {
        Message msg = Message.obtain(null, 1, 2, 3);
        try {
            mService.send(msg);
        } catch (Exception e) {
        }
    }

    private void initView() {
        Fresco.initialize(this);
        mlistView = (ListViewForInter) findViewById(R.id.listview);
        aButton = (AnimatorButton) findViewById(R.id.aButton);
        bindService(new Intent(MainActivity.this, testService.class), connection, Context.BIND_AUTO_CREATE);
    }

    private void initEvent() {
        list = new ArrayList<String>() {{
            add("xieye");
            add("xiee");
            add("xie");
        }};
        final LVAdapter adapter = new LVAdapter(this, list);
        mlistView.setAdapter(adapter);
        mlistView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int firstvisibleitem = mlistView.getFirstVisiblePosition();
                int lastvisibleitem = mlistView.getLastVisiblePosition();
                Log.e("listview is layout", v.getId() + "");
            }
        });
        mlistView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Log.e("addOnAttachStateChange", "onViewAttachedToWindow");
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Log.e("addOnAttachStateChange", "onViewDetachedFromWindow");
            }
        });
        aButton.setOnSClickListener(new AnimatorButton.SClickListener() {
            @Override
            public void onSClick(AnimatorButton.SType type) {
                if (AnimatorButton.SType.MULTI.equals(type)) {
                    aButton.showSendButton();
                    sayHello();
                } else if (AnimatorButton.SType.SEND.equals(type)) {
                    aButton.showMulButton();
                }
            }
        });
    }
}
