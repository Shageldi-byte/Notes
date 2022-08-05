package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Change_Data extends AppCompatActivity {
    EditText edtid;
    ImageButton update,delete;
    DataBaseHelper mydb;
    Context  context=this;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ArrayList<String> menuitem=new ArrayList<>();
        menuitem.add("Maglumatlary görmek");
        menuitem.add("Maglumatlary Üýtgetmek");
        menuitem.add("Programma Barada");
        for(String t:menuitem){
            MenuItem menuItem1=menu.add(t);
            menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getTitle().toString()=="Maglumatlary görmek"){
                        startActivity(new Intent(Change_Data.this,Show_Data.class));
                        finish();
                    }
                    return true;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__data);

        mydb=new DataBaseHelper(this);
        edtid=(EditText)findViewById(R.id.uytgetid);
        delete=(ImageButton)findViewById(R.id.delete);
        update=(ImageButton)findViewById(R.id.update);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedrows=mydb.deleteData(edtid.getText().toString());
                if(deletedrows>0) Toast.makeText(Change_Data.this,"Data DELETED",Toast.LENGTH_SHORT).show();
                else   Toast.makeText(Change_Data.this,"Data not DELETED",Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Change_Data.this,update.class);
                intent.putExtra("id",edtid.getText().toString());
                startActivity(intent);
            }
        });
    }
}
