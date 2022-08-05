package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Nokat extends AppCompatActivity {
    ImageButton goshbtn;
    ImageButton clearbtn;
    EditText resminama_ady;
    NokatDb mydb;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__nokat);
        mydb=new NokatDb(context);
        goshbtn=(ImageButton) findViewById(R.id.add_resminama);
        clearbtn=(ImageButton) findViewById(R.id.clear1);
        resminama_ady=(EditText) findViewById(R.id.resminama_ady);
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resminama_ady.setText("");
            }
        });

        goshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertData(resminama_ady.getText().toString());
                if (isInserted == true)
                    Toast.makeText(context, "Nokat Goşuldy", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "Ýalňyşlyk ýüze çykdy!", Toast.LENGTH_SHORT).show();
                resminama_ady.setText("");

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Add_Nokat.this,Nokat_Ac.class));
    }
}
