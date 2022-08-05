package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Send_data extends AppCompatActivity {
    DataBaseHelper mydb;
    history_of_report_db history;
    Button send;
    Context context=this;
    String ok="",ip="";
    EditText ipadress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
        send = (Button) findViewById(R.id.send);
        mydb = new DataBaseHelper(this);
        history=new history_of_report_db(this);
        ipadress=findViewById(R.id.ipadress);
        SharedPreferences settings=context.getSharedPreferences("ipAdress",Context.MODE_PRIVATE);
        ip=settings.getString("ip",null);
        if(ip!=null)
        {
            ipadress.setText(ip);
        }
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Üns Beriň!!!", "Maglumatlar Bazasy boş!!!");
            return;
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Üns Beriň!!!");
                builder.setMessage("Ähli maglumatlar hasapçynyň kompýuterine ugradylsynmy?");
                builder.setCancelable(false);
                builder.setNegativeButton("Ýok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Howwa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            while (res.moveToNext()) {
                                final String sene = res.getString(1);
                                final String mashyn = res.getString(2);
                                final String nokat = res.getString(3);
                                final String resminama = res.getString(4);
                                final String olceg_birlik = res.getString(5);
                                final String baha = res.getString(6);
                                final String girdeyji_mocberi = res.getString(7);
                                final String girdeyji_pul = res.getString(8);
                                final String galyndy_mocberi = res.getString(9);
                                final String galyndy_pul = res.getString(10);

                                Send send = new Send(context);
                                send.execute("login",sene,mashyn,nokat,resminama,olceg_birlik,baha,girdeyji_mocberi,girdeyji_pul,galyndy_mocberi,galyndy_pul,ipadress.getText().toString());
                                SharedPreferences settings=context.getSharedPreferences("ipAdress",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=settings.edit();
                                editor.putString("ip",ipadress.getText().toString());
                                editor.commit();
                            }

                            SharedPreferences settings=context.getSharedPreferences("netije",Context.MODE_PRIVATE);
                            ok=settings.getString("n",null);

                            //if(ok.equals("1")) {
                                Toast.makeText(context,"Maglumatlar ugradyldy!!!",Toast.LENGTH_LONG).show();
                                Cursor res1 = mydb.getAllData();
                                while (res1.moveToNext()) {
                                    final String sene = res1.getString(1);
                                    final String mashyn = res1.getString(2);
                                    final String nokat = res1.getString(3);
                                    final String resminama = res1.getString(4);
                                    final String olceg_birlik = res1.getString(5);
                                    final String baha = res1.getString(6);
                                    final String girdeyji_mocberi = res1.getString(7);
                                    final String girdeyji_pul = res1.getString(8);
                                    final String galyndy_mocberi = res1.getString(9);
                                    final String galyndy_pul = res1.getString(10);

                                    history.insertData(sene, mashyn, nokat, resminama, olceg_birlik, baha, girdeyji_mocberi, girdeyji_pul, galyndy_mocberi, galyndy_pul);


                                }
                                mydb.all();
                          //  }
                          /*  else{
                                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                                alert.setMessage("Maglumatlary ugratmakda ýalňyňlyk ýüze çykdy!!!");
                                alert.setTitle("ÜNS BERIŇ!!!");
                                alert.setIcon(R.drawable.ic_warning_black_24dp);
                                alert.show();
                            }*/



                        } catch (Exception e){
                           Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                });
                builder.show();

            }
        });




    }




    public void showMessage(String title,String message){

        AlertDialog.Builder builder1=new AlertDialog.Builder(this);
        builder1.setCancelable(true);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(false);
        builder1.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder1.show();
    }












}

