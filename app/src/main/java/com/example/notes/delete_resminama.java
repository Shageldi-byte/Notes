package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class delete_resminama extends AppCompatActivity {
   LinearLayout panel;
   CheckBox checkBox;
   ResminamaDB mydb;
   ArrayList<String> strings=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_resminama);
        panel=(LinearLayout) findViewById(R.id.chekcable);
        mydb=new ResminamaDB(this);
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found");

        }
        while (res.moveToNext()) {
            checkBox = new CheckBox(this);
            checkBox.setText(res.getString(1));
            panel.addView(checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        strings.add(buttonView.getText().toString());
                    }
                    else {
                        strings.remove(buttonView.getText().toString());
                    }
                }
            });



        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.show_data){
            if (strings.toString()!="[]") {
                for (int i = 0; i < strings.size(); i++) {
                    mydb.deleteData(strings.get(i));
                }
                finish();
                startActivity(new Intent(delete_resminama.this,Main3Activity.class));
               Toast.makeText(this,"Resminamalar bazadan öçürildi!",Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(this,"Haryt Sayla",Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(delete_resminama.this,Main3Activity.class));
    }
}
