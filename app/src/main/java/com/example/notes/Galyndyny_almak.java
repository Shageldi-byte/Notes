package com.example.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Galyndyny_almak extends AppCompatActivity {
    List<Galyndy> arrayList;
    List<String> arrayList1;
    history_of_report_db history;
    Spinner spinner;
    Context context=this;
    Button btn_galyndyny_al;
    galyndy_db galyndyDb;
    EditText alynan_aglyndy;
    double galyndy_pul=0,galyndy_mocberi=0,baha=0;
    String nokat="";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galyndyny_almak);
        history=new history_of_report_db(context);
        galyndyDb=new galyndy_db(context);
        spinner=findViewById(R.id.galyndy_spinner);
        alynan_aglyndy=findViewById(R.id.alynan_aglyndy);
        arrayList=new ArrayList<>();
        arrayList=getCustomList();

        btn_galyndyny_al=findViewById(R.id.btn_galyndyny_al);

        Galyndy_Spinner adapter1=new Galyndy_Spinner(this,arrayList);
        if (spinner!=null) {
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Galyndy customItem=arrayList.get(position);
                    galyndy_pul=0;galyndy_mocberi=0;baha=0;
                    nokat=customItem.getNokat();
                   final Cursor cursor=history.getAllData();
                    while(cursor.moveToNext()){
                        if(cursor.getString(3).equals(customItem.getNokat())) {
                            galyndy_pul += Double.parseDouble(cursor.getString(10));
                            galyndy_mocberi += Double.parseDouble(cursor.getString(9));

                        }
                    }
                    Toast.makeText(context,galyndy_pul+" : "+galyndy_mocberi,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        btn_galyndyny_al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(galyndy_pul>=Double.parseDouble(alynan_aglyndy.getText().toString())){
                         if(nokat!=null && Double.parseDouble(alynan_aglyndy.getText().toString())>0){
                             Calendar calendar=Calendar.getInstance();
                             Double yazylan=Double.parseDouble(alynan_aglyndy.getText().toString());
                             int gun=calendar.get(Calendar.DAY_OF_MONTH);
                             int ay=calendar.get(Calendar.MONTH)+1;
                             int yyl=calendar.get(Calendar.YEAR);
                             int sagat=calendar.get(Calendar.HOUR);
                             int minut=calendar.get(Calendar.MINUTE);
                             int sekunt=calendar.get(Calendar.SECOND);
                             String sene=gun+"."+ay+"."+yyl+" "+sagat+":"+minut+":"+sekunt;
                             boolean ugrat=galyndyDb.insertData(nokat,alynan_aglyndy.getText().toString(),sene);
                             if(ugrat){
                                 final Cursor cursor1=history.getAllData();
                                 while(cursor1.moveToNext())
                                 {
                                     if(cursor1.getString(3).equals(nokat))
                                     {
                                         if(yazylan>=Double.parseDouble(cursor1.getString(10))) {
                                         yazylan = yazylan - Double.parseDouble(cursor1.getString(10));
                                         if (yazylan <= 0) {
                                             break;
                                         } else {

                                             baha = Double.parseDouble(cursor1.getString(6));
                                             Double moc = yazylan / baha;


                                         }
                                     }
                                     }
                                 }
                             }

                         }
                     }
            }
        });
    }


    private List<Galyndy> getCustomList() {
      arrayList1=new ArrayList<>();
        Cursor res1 = history.getAllData();
        while (res1.moveToNext()) {
            arrayList1.add(res1.getString(3));

        }
        HashSet<String> set = new HashSet<String>(arrayList1);


        final List<String> result = new ArrayList<>(set);
        for(int i=0;i<result.size();i++)
        {
            arrayList.add(new Galyndy(result.get(i)));
        }
        return arrayList;

    }



}
