package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class harytlary_almak extends AppCompatActivity {
    Button al;
    EditText ip,car;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harytlary_almak);
        al=findViewById(R.id.almak);
        ip=findViewById(R.id.ip);
        car=findViewById(R.id.car);
        SharedPreferences settings=context.getSharedPreferences("ipAdress",Context.MODE_PRIVATE);
        String ip1=settings.getString("ip",null);
        if(ip1!=null)
        {
            ip.setText(ip1);
        }

        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings=context.getSharedPreferences("ipAdress",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=settings.edit();
                editor.putString("ip",ip.getText().toString());
                editor.commit();
                Profile_BackgroundWorker send = new Profile_BackgroundWorker(context,"http://"+ip.getText().toString()+"/haryt_almak.php");
                send.execute("login",car.getText().toString());

            }
        });

    }
}
