package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class edit_nokat extends AppCompatActivity {
    NokatDb mydb;
    Context context=this;
    ListView listView;
    List<String> arrayList=new ArrayList<>();
    List<String> arrayList1=new ArrayList<>();
    List<String> arrayList2=new ArrayList<>();
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resminama);
        listView=(ListView) findViewById(R.id.edit_resminama);
        mydb = new NokatDb(context);
        Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found");

        }
        while (res.moveToNext()) {
            arrayList.add(i+". "+res.getString(1));
            arrayList1.add(res.getString(0));
            arrayList2.add(res.getString(1));
            i++;
        }


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=arrayList1.get(position);
                Intent intent=new Intent(edit_nokat.this,nokat_uytget.class);
                intent.putExtra("id",s);
                intent.putExtra("nokat",arrayList2.get(position));
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(edit_nokat.this,Nokat_Ac.class));
    }
}
