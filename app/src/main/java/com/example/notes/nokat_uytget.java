package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class nokat_uytget extends AppCompatActivity {
    EditText name_resminama;
    Button uytget;
    NokatDb mydb;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nokat_uytget);
        mydb=new NokatDb(this);
        Intent intent=getIntent();
       final String id=intent.getStringExtra("id");
        final String resminama=intent.getStringExtra("nokat");
        uytget=(Button) findViewById(R.id.uyget_resminama);
        name_resminama=(EditText) findViewById(R.id.name_resminama);
        name_resminama.setText(resminama);
        uytget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             boolean isupdate=mydb.updateData(name_resminama.getText().toString(),id);
             if(isupdate){
                 Toast.makeText(context,"Maglumat üýtgedildi",Toast.LENGTH_SHORT).show();
             }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(nokat_uytget.this,edit_nokat.class));
    }
}
