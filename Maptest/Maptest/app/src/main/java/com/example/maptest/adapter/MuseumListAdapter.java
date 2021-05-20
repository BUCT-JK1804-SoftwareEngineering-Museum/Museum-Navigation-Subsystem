package com.example.maptest.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maptest.R;
import com.example.maptest.bean.MuseumListBean;

import java.util.ArrayList;


public class MuseumListAdapter extends BaseAdapter {

    public ArrayList<MuseumListBean.ListBean> listBeans = new ArrayList<>();
    private Context mContext;

    public MuseumListAdapter(ArrayList<MuseumListBean.ListBean> listBeans, Context mContext) {
        this.listBeans = listBeans;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public MuseumListBean.ListBean getItem(int i) {
        return listBeans.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, null);
        initView(convertView, i);
        return convertView;
    }

    private void initView(View convertView, int i) {
        LinearLayout layout = convertView.findViewById(R.id.lay_lay);
        TextView nameTv = convertView.findViewById(R.id.musName_tv);
        TextView userTv = convertView.findViewById(R.id.musMaster_tv);
        TextView timeTv = convertView.findViewById(R.id.time_tv);
        TextView addressTv = convertView.findViewById(R.id.address_tv);
        ImageView imgview = convertView.findViewById(R.id.imgview);
        MuseumListBean.ListBean listBean = listBeans.get(i);
        if(!TextUtils.isEmpty(listBean.getMusPicture())){
            imgview.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(listBean.getMusPicture()).into(imgview);
        }else {
            imgview.setVisibility(View.GONE);
        }
        nameTv.setText(listBean.getMusName());
        userTv.setText(listBean.getMusMaster());
        timeTv.setText(listBean.getMusTime());
        addressTv.setText(listBean.getMusAddress());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener != null) {
                    onItemListener.itemListener(listBean, i);
                }
            }
        });
    }

    public interface OnItemListener {
        void itemListener(MuseumListBean.ListBean bean, int pos);
    }

    public OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener listener) {
        this.onItemListener = listener;
    }
}
