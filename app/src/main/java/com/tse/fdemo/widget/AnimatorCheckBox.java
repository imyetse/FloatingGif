package com.tse.fdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;

/**
 * Created by Tse on 2015/12/18.
 * Email Via tse.y17o@gmail.com
 */
public class AnimatorCheckBox extends CheckBox {
    public AnimatorCheckBox(Context context) {
        super(context);
    }

    public AnimatorCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatorCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("onMeasure", MeasureSpec.getSize(heightMeasureSpec)+"");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
    }

    @Override
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        super.setOnCheckedChangeListener(listener);
    }
}
