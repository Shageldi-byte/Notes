package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class taryh_ichi extends AppCompatActivity {
    history_of_report_db mydb;
    int k=0;
    ArrayList<mut> maglumatlar=new ArrayList<>();
    ListView listView;
    Context context=this;
    String s,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taryh_ichi);
        Intent intent=getIntent();
        s=intent.getStringExtra("s");
        result=intent.getStringExtra("result");

        mydb = new history_of_report_db(this);
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found");
            return;
        }
        while (res.moveToNext()) {

            if(s.equals("1")){
                if(res.getString(1).equals(result)){
                    maglumatlar.add(new mut(res.getString(0),res.getString(1),
                            res.getString(2),res.getString(3),res.getString(4),
                            res.getString(5),res.getString(6),res.getString(7),
                            res.getString(8),res.getString(9),res.getString(10)));
                }
            }

            if(s.equals("2")){
                if(res.getString(3).equals(result)){
                    maglumatlar.add(new mut(res.getString(0),res.getString(1),
                            res.getString(2),res.getString(3),res.getString(4),
                            res.getString(5),res.getString(6),res.getString(7),
                            res.getString(8),res.getString(9),res.getString(10)));
                }
            }

            if(s.equals("3")){
                if(res.getString(4).equals(result)){
                    maglumatlar.add(new mut(res.getString(0),res.getString(1),
                            res.getString(2),res.getString(3),res.getString(4),
                            res.getString(5),res.getString(6),res.getString(7),
                            res.getString(8),res.getString(9),res.getString(10)));
                }
            }



        }
        listView=(ListView) findViewById(R.id.list_ichi);
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

}
