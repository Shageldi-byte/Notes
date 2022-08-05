package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class bazadaky_harytlar extends AppCompatActivity {
    ArrayList<harytlar_class> harytlarClassArrayList=new ArrayList<>();
    Context context=this;
    ListView lst;
    harytlardb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazadaky_harytlar);
        db=new harytlardb(context);
        lst=findViewById(R.id.baza_haryt);
        final Cursor res = db.getAllData();
        if (res.getCount() == 0) {
//
            Toast.makeText(context,"Baza bosh",Toast.LENGTH_SHORT).show();
            return;
        }

        while(res.moveToNext()){
            double jemi=0;
            jemi=Double.parseDouble(res.getString(2))*Double.parseDouble(res.getString(3));
            harytlarClassArrayList.add(new harytlar_class(res.getString(0),res.getString(1),res.getString(2),res.getString(3),String.valueOf(jemi)));


        }

        custom_baza_haryt adapter=new custom_baza_haryt(harytlarClassArrayList,context);
        lst.setAdapter(adapter);





    }
}
