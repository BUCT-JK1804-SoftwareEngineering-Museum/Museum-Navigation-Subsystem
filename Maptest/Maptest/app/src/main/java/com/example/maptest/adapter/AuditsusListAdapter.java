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
import com.example.maptest.bean.AuditstusListBean;

import java.util.ArrayList;


public class AuditsusListAdapter extends BaseAdapter {

    public ArrayList<AuditstusListBean.ListBean> listBeans = new ArrayList<>();
    private Context mContext;

    public AuditsusListAdapter(ArrayList<AuditstusListBean.ListBean> listBeans, Context mContext) {
        this.listBeans = listBeans;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public AuditstusListBean.ListBean getItem(int i) {
        return listBeans.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.auditsus_list_item_layout, null);
        initView(convertView, i);
        return convertView;
    }

    private void initView(View convertView, int i) {
        LinearLayout layout = convertView.findViewById(R.id.lay_lay);
        TextView nameTv = convertView.findViewById(R.id.musName_tv);
        TextView stateTv = convertView.findViewById(R.id.state_tv);
        TextView timeTv = convertView.findViewById(R.id.time_tv);
        TextView addressTv = convertView.findViewById(R.id.address_tv);
        AuditstusListBean.ListBean listBean = listBeans.get(i);
        nameTv.setText(listBean.getMusName());
        stateTv.setText(String.valueOf(listBean.getVidStatus()).replace("1","未审核").replace("2","审核未通过").replace("3","审核通过"));
        timeTv.setText(listBean.getVidTime());
        addressTv.setText(listBean.getVidName());
    }

    public interface OnItemListener {
        void itemListener(AuditstusListBean.ListBean bean, int pos);
    }

    public OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener listener) {
        this.onItemListener = listener;
    }
}
