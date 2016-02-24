package com.tse.fdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nineoldandroids.animation.AnimatorSet;
import com.tse.fdemo.controller.adapter.LVAdapter;
import com.tse.fdemo.widget.AnimatorButton;
import com.tse.fdemo.widget.ListViewForInter;

import java.util.ArrayList;
import java.util.List;

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

    private void initView() {
        Fresco.initialize(this);
        mlistView = (ListViewForInter) findViewById(R.id.listview);
        aButton = (AnimatorButton) findViewById(R.id.aButton);
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
                } else if (AnimatorButton.SType.SEND.equals(type)) {
                    aButton.showMulButton();
                }
            }
        });
    }
}
