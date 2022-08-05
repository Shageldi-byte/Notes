package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Hasabatlar_Taryhy extends AppCompatActivity {
    ListView list_taryh;
    history_of_report_db mydb;
    String s1="1";
    ArrayList<String> taryhArrayList1=new ArrayList<>();
    Context context=this;

    LinearLayout sene,nokat,resminama;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taryh_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.statistika:
                Intent intent=new Intent(context,taryh_statistika.class);
                intent.putExtra("id","1");
                intent.putExtra("result","1");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasabatlar__taryhy);
        list_taryh=findViewById(R.id.list_taryh);
        sene=findViewById(R.id.sene_btn);
        nokat=findViewById(R.id.nokat_btn);
        resminama=findViewById(R.id.resminama_btn);

        mydb = new history_of_report_db(this);
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("ÜNS BERIŇ!!!", "Maglumatlar bazasy boş!");
            return;
        }
        sene1();

        sene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sene.setBackgroundColor(Color.parseColor("#15981B"));
                nokat.setBackgroundColor(Color.parseColor("#2680EB"));
                resminama.setBackgroundColor(Color.parseColor("#2680EB"));
                sene1();
                s1="1";
            }
        });

        nokat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sene.setBackgroundColor(Color.parseColor("#2680EB"));
                nokat.setBackgroundColor(Color.parseColor("#15981B"));
                resminama.setBackgroundColor(Color.parseColor("#2680EB"));
                nokat1();
                s1="2";
            }
        });


        resminama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sene.setBackgroundColor(Color.parseColor("#2680EB"));
                nokat.setBackgroundColor(Color.parseColor("#2680EB"));
                resminama.setBackgroundColor(Color.parseColor("#15981B"));
                resminama1();
                s1="3";
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

    public void sene1(){
        taryhArrayList1.clear();
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("ÜNS BERIŇ!!!", "Maglumatlar bazasy boş!");
            return;
        }
        while (res.moveToNext()) {

            taryhArrayList1.add(res.getString(1));
        }




        HashSet<String> set = new HashSet<String>(taryhArrayList1);


        final ArrayList<String> result = new ArrayList<>(set);








        Custom_Taryh adapter=new Custom_Taryh(result,this);
        list_taryh.setAdapter(adapter);


        list_taryh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,result.get(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,taryh_ichi.class);
                intent.putExtra("s",s1);
                intent.putExtra("result",result.get(position));
                startActivity(intent);
            }
        });
    }



    public void nokat1(){
        taryhArrayList1.clear();
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("ÜNS BERIŇ!!!", "Maglumatlar bazasy boş!");
            return;
        }
        while (res.moveToNext()) {

            taryhArrayList1.add(res.getString(3));
        }





        HashSet<String> set = new HashSet<String>(taryhArrayList1);


        final ArrayList<String> result = new ArrayList<>(set);









        Custom_Taryh adapter=new Custom_Taryh(result,this);
        list_taryh.setAdapter(adapter);


        list_taryh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,result.get(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,taryh_ichi.class);
                intent.putExtra("s",s1);
                intent.putExtra("result",result.get(position));
                startActivity(intent);
            }
        });
    }


    public void resminama1(){
        taryhArrayList1.clear();
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("ÜNS BERIŇ!!!", "Maglumatlar bazasy boş!");
            return;
        }
        while (res.moveToNext()) {

             taryhArrayList1.add(res.getString(4));
        }






        HashSet<String> set = new HashSet<String>(taryhArrayList1);


        final ArrayList<String> result = new ArrayList<>(set);









        Custom_Taryh adapter=new Custom_Taryh(result,this);
        list_taryh.setAdapter(adapter);


        list_taryh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,result.get(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,taryh_ichi.class);
                intent.putExtra("s",s1);
                intent.putExtra("result",result.get(position));
                startActivity(intent);
            }
        });
    }
}
