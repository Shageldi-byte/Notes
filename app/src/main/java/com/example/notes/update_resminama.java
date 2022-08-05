package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_resminama extends AppCompatActivity {
    EditText name_resminama;
    Button uytget;
    ResminamaDB mydb;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_resminama);
        mydb=new ResminamaDB(this);
        Intent intent=getIntent();
       final String id=intent.getStringExtra("id");
        final String resminama=intent.getStringExtra("resminama");
        uytget=(Button) findViewById(R.id.uyget_resminama);
        name_resminama=(EditText) findViewById(R.id.name_resminama);
        name_resminama.setText(resminama);
        uytget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             boolean isupdate=mydb.updateData(name_resminama.getText().toString(),id,"0");
             if(isupdate){
                 Toast.makeText(context,"Maglumat üýtgedildi",Toast.LENGTH_SHORT).show();
             }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(update_resminama.this,edit_resminama.class));
    }
}
