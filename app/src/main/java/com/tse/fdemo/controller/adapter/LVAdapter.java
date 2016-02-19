package com.tse.fdemo.controller.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tse.fdemo.R;
import com.tse.fdemo.widget.AnimatorCheckBox;
import com.tse.fdemo.widget.GiftPopupWindow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tse on 2015/12/14.
 * Email Via tse.y17o@gmail.com
 */
public class LVAdapter extends BaseAdapter implements View.OnClickListener {

    private List<String> list;
    private Activity activity;
    private GiftPopupWindow giftPW;
    private boolean showDelete = false;
    private Map<Integer, Boolean> map = new HashMap<>();

    public LVAdapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
        giftPW = new GiftPopupWindow().getBuilder(activity);
    }

    public void showDeleteButton() {
        showDelete = true;
        map.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textView = (TextView) convertView.findViewById(R.id.name);
            holder.draweeView = (SimpleDraweeView) convertView.findViewById(R.id.draweeView);
            holder.checkBox = (AnimatorCheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (showDelete) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    map.put(position, isChecked);
                }
            });
            if (map.get(position) != null && map.get(position)) {
                holder.checkBox.setChecked(true);
                Log.e("checkBox is Checked", position + "");
            }else{
                holder.checkBox.setChecked(false);
            }
        }
        holder.textView.setText(list.get(position));
        holder.draweeView.setOnClickListener(this);
        //该类的入口
        giftPW.initAndExecute(holder.draweeView);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Log.e("i m a widget", "and i was clicked.my id is:" + v.getId());
    }


    class ViewHolder {
        private SimpleDraweeView draweeView;
        private TextView textView;
        private ImageView imageView;
        private AnimatorCheckBox checkBox;
    }
}
