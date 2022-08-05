package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Custom_Adapter_Olceg extends ArrayAdapter {
    public Custom_Adapter_Olceg(@NonNull Context context, ArrayList<Custom_Olceg_birlik> customlist) {
        super(context, 0, customlist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_olceg_birlik_spinner_layout,parent,false);

        }
        Custom_Olceg_birlik item= (Custom_Olceg_birlik) getItem(position);
        ImageView spinnerIV=convertView.findViewById(R.id.ivSpinnerLayout);
        TextView spinnerTv=convertView.findViewById(R.id.tvSpinnerLayout);
        if (item!=null) {
            spinnerIV.setImageResource(item.getSurat());
            spinnerTv.setText(item.getOlceg_birlik());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_olceg_birlik_dropdown_layout,parent,false);

        }
        Custom_Olceg_birlik item= (Custom_Olceg_birlik) getItem(position);
        ImageView dropDownIV=convertView.findViewById(R.id.ivDropDownLayout);
        TextView dropDownTV=convertView.findViewById(R.id.tvDropDownLayout);
        if (item!=null) {
            dropDownIV.setImageResource(item.getSurat());
            dropDownTV.setText(item.getOlceg_birlik());
        }
        return convertView;
    }
}
