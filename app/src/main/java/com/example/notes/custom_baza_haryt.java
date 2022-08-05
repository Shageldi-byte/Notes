package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class custom_baza_haryt extends BaseAdapter {
    ArrayList<harytlar_class> harytlarClassArrayList=new ArrayList<>();
    LayoutInflater inflater;
    Context context;

    public custom_baza_haryt(ArrayList<harytlar_class> harytlarClassArrayList, Context context) {
        this.harytlarClassArrayList = harytlarClassArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return harytlarClassArrayList.size();
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
        harytlar_class hr=harytlarClassArrayList.get(position);
        View view=inflater.inflate(R.layout.harytlar_tb,null);
        TextView id=view.findViewById(R.id.tb_baza);
        TextView haryt_ady=view.findViewById(R.id.haryt_ady);
        TextView baha_baza=view.findViewById(R.id.baha_baza);
        TextView sany=view.findViewById(R.id.sany);
        TextView jemi=view.findViewById(R.id.jemi);
        id.setText(hr.getId());
        haryt_ady.setText(hr.getHaryt_ady());
        baha_baza.setText(hr.getBaha());
        sany.setText(hr.getSany());
        jemi.setText(hr.getJemi());
        return view;
    }
}
