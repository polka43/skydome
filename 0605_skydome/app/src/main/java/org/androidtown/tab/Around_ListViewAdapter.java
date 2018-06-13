package org.androidtown.tab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.ArrayList;

/**
 * Created by 215 on 2018-05-09.
 */

public class Around_ListViewAdapter extends BaseAdapter {
    private ArrayList<Around_ListViewItem> arItem;
    private View.OnClickListener telClickListener;
    private View.OnClickListener mapClickListener;

    private ViewGroup context;
    private AQuery aQuery;

    public Around_ListViewAdapter(ViewGroup context) {
        this.arItem = new ArrayList<Around_ListViewItem>();
        this.context = context;
        aQuery = new AQuery(context);
    }

    public ArrayList<Around_ListViewItem> getArItem(){return arItem;}
    public void setTelClickListener(View.OnClickListener telClickListener){
        this.telClickListener = telClickListener;
    }
    public void setMapClickListener(View.OnClickListener mapClickListener){
        this.mapClickListener = mapClickListener;
    }


    @Override
    public int getCount() {
        return arItem.size();
    }

    @Override
    public Around_ListViewItem getItem(int position) {
        return arItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.around_row, parent, false);
        }

        Around_ListViewItem item = arItem.get(position);

        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhoto);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.tvAdress);
        TextView tvTel = (TextView) convertView.findViewById(R.id.tvTel);
        Button btnMap = (Button)convertView.findViewById(R.id.btnMap);
        Button btnTel = (Button)convertView.findViewById(R.id.btnTel);

        aQuery.id(ivPhoto).image(item.getFirstimage(),true,false);
        tvTitle.setText(item.getTitle());
        tvAddress.setText(item.getAddress());
        tvTel.setText(item.getTel());

        btnTel.setTag(position);
        if(telClickListener != null){
            btnTel.setOnClickListener(telClickListener);
        }

        btnMap.setTag(position);
        if(mapClickListener!=null){
            btnMap.setOnClickListener(mapClickListener);
        }
        return convertView;

    }

}
