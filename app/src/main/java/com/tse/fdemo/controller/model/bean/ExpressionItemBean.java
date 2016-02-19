package com.tse.fdemo.controller.model.bean;

import android.graphics.Rect;
import android.view.View;

import java.io.Serializable;

/**
 * Created by Tse on 2015/12/16.
 * Email Via tse.y17o@gmail.com
 */
public class ExpressionItemBean implements Serializable {

    private static final long serialVersionUID = 5126600910175155950L;
    private Rect rect;
    private View view;
    private String uri;

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String toString() {
        return rect.left + "/" + rect.top + "/" + rect.right + "/" + rect.bottom;
    }
}
