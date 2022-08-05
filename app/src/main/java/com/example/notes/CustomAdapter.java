package com.example.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<mut> maglumatlar=new ArrayList<>();
    Context context;
    int j=0;
    DataBaseHelper mydb;

    public CustomAdapter(ArrayList<mut> maglumatlar, Context context) {
        this.maglumatlar = maglumatlar;
        this.context = context;
    }

    @Override
    public int getCount() {
        return maglumatlar.size();
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
        mydb=new DataBaseHelper(context);
        final mut m=maglumatlar.get(position);
        View view=inflater.inflate(R.layout.maglumatlar,null);
        TextView tb = (TextView) view.findViewById(R.id.tb);
        tb.setText(m.getId());



        TextView sene = (TextView) view.findViewById(R.id.senetxt);
        sene.setText("Sene: "+ m.getSene());
        TextView mashyn = (TextView) view.findViewById(R.id.mashyntxt);
        mashyn.setText("Mashyn: "+ m.getMashyn());
        TextView nokat = (TextView) view.findViewById(R.id.nokattxt);
        nokat.setText("Nokat: "+ m.getNokat());
        TextView resminama = (TextView) view.findViewById(R.id.resminamatxt);
        resminama.setText("Resminama: "+ m.getResminama());
        TextView olceg_birlik = (TextView) view.findViewById(R.id.olceg_birliktxt);
        olceg_birlik.setText("Ölçeg birlik: "+ m.getOlceg_birlik());
        TextView baha = (TextView) view.findViewById(R.id.bahatxt);
        baha.setText("Baha: "+ m.getBaha()+" manat");
        TextView girdeyji_mocberi = (TextView) view.findViewById(R.id.girdeyji_mocberitxt);
        girdeyji_mocberi.setText("Girdedyji Mocberi: "+ m.getGirdeyji_mocberi()+" sany "+m.getOlceg_birlik());
        TextView girdeyji_pul = (TextView) view.findViewById(R.id.girdeyji_pultxt);
        girdeyji_pul.setText("Girdeyji Pul: "+ m.getGirdeyji_pul()+" manat");
        TextView galyndy_mocberi = (TextView) view.findViewById(R.id.galyndy_mocberitxt);
        galyndy_mocberi.setText("Galyndy Mocberi: "+ m.getGalyndy_mocberi()+" sany "+m.getOlceg_birlik());
        TextView galyndy_pul = (TextView) view.findViewById(R.id.galyndy_pultxt);
        galyndy_pul.setText("Galyndy Pul: "+ m.getGalyndy_pul()+" manat");
        return view;
    }
}
