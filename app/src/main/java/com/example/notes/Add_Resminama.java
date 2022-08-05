package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Add_Resminama extends AppCompatActivity {
    ImageButton goshbtn;
    ImageButton clearbtn;
    EditText resminama_ady;
    ResminamaDB mydb;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__resminama);
        mydb=new ResminamaDB(context);
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
                boolean isInserted = mydb.insertData(resminama_ady.getText().toString(),"0");
                if (isInserted == true)
                    Toast.makeText(context, "Resminama Goşuldy", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "Ýalňyşlyk ýüze çykdy!", Toast.LENGTH_SHORT).show();
                resminama_ady.setText("");

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Add_Resminama.this,Main3Activity.class));
    }
}
