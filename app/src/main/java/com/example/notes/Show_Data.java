package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Show_Data extends AppCompatActivity {
    DataBaseHelper mydb;
    int k=0;
    ArrayList<mut> maglumatlar=new ArrayList<>();
    ListView listView;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__data);
        mydb = new DataBaseHelper(this);
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("ÜNS BERIŇ!!!", "Maglumatlar bazasy boş!");
            return;
        }
        while (res.moveToNext()) {


            maglumatlar.add(new mut(res.getString(0),res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4),
                    res.getString(5),res.getString(6),res.getString(7),
                    res.getString(8),res.getString(9),res.getString(10)));

        }
        listView=(ListView) findViewById(R.id.list);
        CustomAdapter adapter=new CustomAdapter(maglumatlar, context);
        listView.setAdapter(adapter);

    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void sta(View v)
    {
        Intent intent=new Intent(context,Hasabatlar_Statistika.class);
        intent.putExtra("id","1");
        intent.putExtra("result","1");
        startActivity(intent);
    }
}
