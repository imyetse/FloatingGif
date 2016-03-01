package com.tse.fdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.tse.fdemo.R;

/**
 * Created by Tse on 2016/2/22.
 * Email Via imyetse@gmail.com
 */
public class AnimatorButton extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private Button sbutton;//发送按钮
    private Button mbutton;//更多按钮

    private int height;
    private int width;


    public AnimatorButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatorButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.aButton, defStyleAttr, 0);
        height = a.getDimensionPixelOffset(R.styleable.aButton_aheight, 80);
        width = a.getDimensionPixelOffset(R.styleable.aButton_awidth, 120);
        init();
    }


    public void init() {
        int resylt = Math.min(height,width);
        mbutton = new Button(context);
        mbutton.setId(R.id.multi_id);
        mbutton.setOnClickListener(AnimatorButton.this);
        mbutton.setGravity(Gravity.CENTER);
        mbutton.setBackgroundResource(R.drawable.icon_add_nomal);
        LayoutParams mparams = new LayoutParams(resylt, resylt);
        mparams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        addView(mbutton, 0, mparams);
        sbutton = new Button(context);
        sbutton.setId(R.id.send_id);
        sbutton.setOnClickListener(AnimatorButton.this);
        sbutton.setPadding(8, 8, 8, 8);
        sbutton.setText("发送");
        sbutton.setTextColor(context.getResources().getColor(R.color.white));
        sbutton.setBackgroundResource(R.drawable.button_send_selector);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(sbutton, 1, params);
        getChildAt(1).setVisibility(INVISIBLE);

    }


    public void showMulButton() {
        if (getChildAt(0).getVisibility() == VISIBLE)
            return;


        ObjectAnimator animIn1 = ObjectAnimator.ofFloat(sbutton, "scaleX", 1.0f, 0.8f);
        ObjectAnimator animIn2 = ObjectAnimator.ofFloat(sbutton, "scaleY", 1.0f, 0.8f);
        ObjectAnimator animIn3 = ObjectAnimator.ofFloat(sbutton, "alpha", 1.0f, 0f);

        ObjectAnimator animIn4 = ObjectAnimator.ofFloat(mbutton, "scaleX", 0.9f, 1.0f);
        ObjectAnimator animIn5 = ObjectAnimator.ofFloat(mbutton, "scaleY", 0.9f, 1.0f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animIn1, animIn2, animIn3, animIn4, animIn5);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                getChildAt(0).setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                getChildAt(1).setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.setDuration(100);
        set.start();
    }

    public void showSendButton() {
        if (getChildAt(1).getVisibility() == VISIBLE)
            return;

        ObjectAnimator animIn4 = ObjectAnimator.ofFloat(sbutton, "scaleX", 0.9f, 1.0f);
        ObjectAnimator animIn5 = ObjectAnimator.ofFloat(sbutton, "scaleY", 0.9f, 1.0f);
        ObjectAnimator animIn6 = ObjectAnimator.ofFloat(sbutton, "alpha", 0.1f, 1.0f);

        AnimatorSet set = new AnimatorSet();

        set.playTogether(animIn4, animIn5, animIn6);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                getChildAt(1).setAlpha(0);
                getChildAt(1).setVisibility(VISIBLE);
                getChildAt(0).setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.setDuration(100);
        set.start();
    }

    @Override
    public void onClick(View v) {
        do {
            if (v.getId() == R.id.send_id) {
                if (sClickListener != null) {
                    sClickListener.onSClick(SType.SEND);
                }
                break;
            }
            if (v.getId() == R.id.multi_id) {
                if (sClickListener != null) {
                    sClickListener.onSClick(SType.MULTI);
                }
                break;
            }
        } while (false);
    }

    public void setOnSClickListener(SClickListener sListener) {
        this.sClickListener = sListener;
    }

    private SClickListener sClickListener;

    public interface SClickListener {
        void onSClick(SType type);
    }

    public enum SType {
        SEND("send"), MULTI("multi");
        private String type;

        SType(String type) {
            this.type = type;
        }
    }
}
