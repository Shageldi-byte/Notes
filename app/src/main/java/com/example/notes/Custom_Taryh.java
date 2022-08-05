package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_Taryh extends BaseAdapter {
    ArrayList<String> taryhArrayList=new ArrayList<>();
    LayoutInflater inflater;
    Context context;

    public Custom_Taryh(ArrayList<String> taryhArrayList, Context context) {
        this.taryhArrayList = taryhArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return taryhArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.taryh,null);
        TextView sene=view.findViewById(R.id.sene);
        sene.setText(taryhArrayList.get(position));

        return view;
    }
}
