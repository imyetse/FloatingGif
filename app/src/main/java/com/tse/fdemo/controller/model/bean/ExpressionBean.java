package com.tse.fdemo.controller.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tse on 2015/12/16.
 * Email Via tse.y17o@gmail.com
 */
public class ExpressionBean implements Serializable {

    private static final long serialVersionUID = -1999213154017030908L;
    private List<ExpressionItemBean> contentList;

    public List<ExpressionItemBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<ExpressionItemBean> contentList) {
        this.contentList = contentList;
    }
}
