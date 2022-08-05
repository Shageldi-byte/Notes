package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class update extends AppCompatActivity {
    EditText mashyn,nokat,resminama,olceg_birlik,baha,girdeyji_mocberi,girdeyji_pul,idedit;
    Button uytgetmek;
    Context context=this;
    int ww=1;
    ArrayList<String> arrayList=new ArrayList<>();



    double haryt_bahasy,satylan_haryt_sany,alynan_pul,galan_pul,umumy_almaly_pul,galan_haryt_sany;
    String car,positions,resmi,sene="01.30.2020",cykdayjy_mocberi="10",cykdayjy_pul="100",galyndy_pul="500",galyndy_mocberi="10";
    DataBaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ishle();
        final Calendar calendar=Calendar.getInstance();
        mydb = new DataBaseHelper(this);
        Intent intent=getIntent();
       final String id=intent.getStringExtra("id");
        Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found");
            return;
        }
        while (res.moveToNext()) {
            if(res.getString(0).equals(id)) {
                idedit.setText(res.getString(0));
                mashyn.setText(res.getString(2));
                nokat.setText(res.getString(3));
                resminama.setText(res.getString(4));
                olceg_birlik.setText(res.getString(5));
                baha.setText(res.getString(6));
                girdeyji_mocberi.setText(res.getString(7));
                girdeyji_pul.setText(res.getString(8));
                ww=1;
                break;
            }
            else
            {
                ww=0;
            }
        }
        if(ww==0){
            Toast.makeText(context,"Beýle id ýok!!!",Toast.LENGTH_SHORT).show();
            finish();
        }
        uytgetmek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     sene= calendar.get(Calendar.DAY_OF_MONTH)+"."+(calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.YEAR);
                if(mashyn.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Maşyn belgiňizi giriziň!!!",Toast.LENGTH_SHORT).show();
                    mashyn.requestFocus();
                    return;
                }if(idedit.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"ID giriziň!!!",Toast.LENGTH_SHORT).show();
                    idedit.requestFocus();
                    return;
                }
                if(nokat.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Nokady giriziň!!!",Toast.LENGTH_SHORT).show();
                    nokat.requestFocus();
                    return;
                }
                if(resminama.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Resminamany giriziň!!!",Toast.LENGTH_SHORT).show();
                    resminama.requestFocus();
                    return;
                }
                if(olceg_birlik.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Ölçeg birligi giriziň!!!",Toast.LENGTH_SHORT).show();
                    olceg_birlik.requestFocus();
                    return;
                }
                if(baha.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Bahany giriziň!!!",Toast.LENGTH_SHORT).show();
                    baha.requestFocus();
                    return;
                }
                if(girdeyji_mocberi.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Girdeýji möçberini giriziň!!!",Toast.LENGTH_SHORT).show();
                    girdeyji_mocberi.requestFocus();
                    return;
                }
                if(girdeyji_pul.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Girdeýji puly giriziň!!!",Toast.LENGTH_SHORT).show();
                    girdeyji_pul.requestFocus();
                    return;
                }

                try{
                    Calendar hazirkiwagt=Calendar.getInstance();
                    int yyl=hazirkiwagt.get(Calendar.YEAR);
                    int ay=hazirkiwagt.get(Calendar.MONTH);
                    int gun=hazirkiwagt.get(Calendar.DAY_OF_MONTH);
                    sene=gun+"."+ay+1+"."+yyl;
                    car=mashyn.getText().toString();
                    positions=nokat.getText().toString();
                    resmi=resminama.getText().toString();
                    haryt_bahasy=Double.parseDouble(baha.getText().toString());
                    satylan_haryt_sany=Double.parseDouble(girdeyji_mocberi.getText().toString());
                    alynan_pul=Double.parseDouble(girdeyji_pul.getText().toString());
                    umumy_almaly_pul=(satylan_haryt_sany*haryt_bahasy);
                    if(umumy_almaly_pul>=alynan_pul)
                        galan_pul=umumy_almaly_pul-alynan_pul;
                    else
                        Toast.makeText(context,"Siz artykmaç pul aldyňyz",Toast.LENGTH_SHORT).show();
                    galan_haryt_sany=Math.abs(satylan_haryt_sany-(alynan_pul/haryt_bahasy));

                    AlertDialog.Builder alert=new AlertDialog.Builder(context);

                    alert.setTitle("Maglumatlar").setMessage("Sene"+sene+"\nMaşyn: "+mashyn.getText().toString()+"\nNokat: "+nokat.getText().toString()+"\nResminama: "+resminama.getText().toString()
                            +"\nBaha: "+haryt_bahasy+"\nGirdeyji Mocberi: "+satylan_haryt_sany+"\nÖlçeg birlik: "+olceg_birlik.getText().toString()+"\nGirdeyji pul: "+alynan_pul+"\nUmumy alynmaly pul: "+umumy_almaly_pul
                            +"\nGalan pul: "+galan_pul+"\nGalan haryt sany: "+galan_haryt_sany).show();

                    boolean isUpdated= mydb.updateData(idedit.getText().toString(),id,sene,car,positions,resmi,olceg_birlik.getText().toString(),baha.getText().toString(),girdeyji_mocberi.getText().toString(),
                            girdeyji_pul.getText().toString(),String.valueOf(galan_haryt_sany),String.valueOf(galan_pul));
                    if(isUpdated==true)
                        Toast.makeText(update.this,"Data Updated",Toast.LENGTH_SHORT).show();
                    else   Toast.makeText(update.this,"Data not Updated",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(context,"Your phone sad "+e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void ishle(){
        mashyn=(EditText)findViewById(R.id.mashynuytget);
        idedit=(EditText)findViewById(R.id.iduyteg);
        nokat=(EditText)findViewById(R.id.nokatuytget);
        resminama=(EditText)findViewById(R.id.resminamauytget);
        olceg_birlik=(EditText)findViewById(R.id.olceg_birlikuytget);
        baha=(EditText)findViewById(R.id.bahauytget);
        girdeyji_mocberi=(EditText)findViewById(R.id.girdeyji_mpcberiuytget);
        girdeyji_pul=(EditText)findViewById(R.id.girdeyji_puluytget);
        uytgetmek=(Button)findViewById(R.id.uyget);
    }
}
