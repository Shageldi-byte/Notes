package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.IconCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText resminama,olceg_birlik,girdeyji_mocberi,girdeyji_pul;
    TextView baha,mashyn;
    ImageButton hasaba_al,clearall;
    ImageView imageView;
    int i=0;
    double san;
    String nokat1="",resminamat="",olceg_bir="",sann="",tb="";
    ArrayList<CustomItem> arrayList;
    ArrayList<CustomResminama> arrayList1;
    ArrayList<Custom_Olceg_birlik> arrayList2;
    AnimationDrawable animationDrawable;
    Context context=this;
    Spinner nokat,resminamaspinner,olceg_birlik_spinner;
    double haryt_bahasy,satylan_haryt_sany,alynan_pul,galan_pul,umumy_almaly_pul,galan_haryt_sany;
    String car,positions,resmi,sene="01.30.2020",cykdayjy_mocberi="10",cykdayjy_pul="100",galyndy_pul="500",galyndy_mocberi="10";
    DataBaseHelper mydb;
    NokatDb mydb1;
    harytlardb mydb2;
    OlcegBirlikDb olcegBirlikDb;
    history_of_report_db history;
    double galan,towar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        ishle();
        nokat=(Spinner)findViewById(R.id.customIconSpinner);
        resminamaspinner=(Spinner)findViewById(R.id.resminama_spinner);
        olceg_birlik_spinner=(Spinner)findViewById(R.id.olceg_birlik_spinner);
        mydb=new DataBaseHelper(this);
        mydb1 = new NokatDb(this);
        mydb2 = new harytlardb(this);
        olcegBirlikDb=new OlcegBirlikDb(context);
        olcegBirlikDb.all();
        olcegBirlikDb.insertData("Karopka");
        history=new history_of_report_db(context);








        arrayList=getCustomList();
        arrayList1=getCustomList1();
        arrayList2=getCustomList2();
        Custom_Adapter adapter1=new Custom_Adapter(this,arrayList);
        if (nokat!=null) {
            nokat.setAdapter(adapter1);
            nokat.setOnItemSelectedListener(this);
        }

        Custom_Resminama_Adapter adapter2=new Custom_Resminama_Adapter(this,arrayList1);
        if (resminamaspinner!=null) {
            resminamaspinner.setAdapter(adapter2);

        }

        Custom_Adapter_Olceg adapter3=new Custom_Adapter_Olceg(this,arrayList2);
        if (olceg_birlik_spinner!=null) {
            olceg_birlik_spinner.setAdapter(adapter3);

        }
        olceg_birlik_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Custom_Olceg_birlik custom_olceg_birlik=(Custom_Olceg_birlik) arrayList2.get(position);
                olceg_bir=custom_olceg_birlik.getOlceg_birlik();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        resminamaspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CustomResminama customResminama=(CustomResminama) arrayList1.get(position);
                resminamat=customResminama.getResminama();
                baha.setText(customResminama.getBaha());
                sann=customResminama.getSany();
                tb=customResminama.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                girdeyji_mocberi.setText("");
                girdeyji_pul.setText("");
            }
        });


        hasaba_al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=mashyn.getText().toString();
                if(s.isEmpty())
                {
                    Toast.makeText(context,"Maşyn belgiňizi giriziň!!!",Toast.LENGTH_SHORT).show();
                    return;
                }


                if(baha.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Bahany giriziň!!!",Toast.LENGTH_SHORT).show();
                    baha.requestFocus();
                    return;
                }
                if(girdeyji_mocberi.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Girdeýji möçberini giriziň!!!",Toast.LENGTH_SHORT).show();
                    girdeyji_mocberi.requestFocus();
                    return;
                }
                if(resminamat.equals("")){
                    Toast.makeText(context,"Resminama sayla",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nokat1.equals("")){
                    Toast.makeText(context,"Nokat sayla",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (olceg_bir.equals("")){
                    Toast.makeText(context,"Olceg birlik sayla",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(girdeyji_pul.getText().toString().isEmpty())
                {
                    Toast.makeText(context,"Girdeýji puly giriziň!!!",Toast.LENGTH_SHORT).show();
                    girdeyji_pul.requestFocus();
                    return;
                }
                if(Double.parseDouble(girdeyji_mocberi.getText().toString())<=0 && Double.parseDouble(girdeyji_pul.getText().toString())<=0)
                {
                    Toast.makeText(context,"Girdeýji nädogry!!!",Toast.LENGTH_SHORT).show();
                    return;
                }

                try{
                    if(Double.parseDouble(girdeyji_mocberi.getText().toString())<=0 || Double.parseDouble(girdeyji_pul.getText().toString())<=0)
                    {
                        Toast.makeText(context,"Nädogry möçber girizdiňiz!!!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    haryt_bahasy = Double.parseDouble(baha.getText().toString());
                    satylan_haryt_sany = Double.parseDouble(girdeyji_mocberi.getText().toString());
                    alynan_pul = Double.parseDouble(girdeyji_pul.getText().toString());
                    umumy_almaly_pul = (satylan_haryt_sany * haryt_bahasy);

                    if(umumy_almaly_pul>=alynan_pul) {
                        Calendar hazirkiwagt = Calendar.getInstance();
                        int yyl = hazirkiwagt.get(Calendar.YEAR);
                        int ay = hazirkiwagt.get(Calendar.MONTH) + 1;
                        int gun = hazirkiwagt.get(Calendar.DAY_OF_MONTH);
                        sene = gun + "." + ay + "." + yyl;
                        car = s;
                        positions = nokat1;
                        resmi = resminamat;
                        haryt_bahasy = Double.parseDouble(baha.getText().toString());
                        satylan_haryt_sany = Double.parseDouble(girdeyji_mocberi.getText().toString());
                        alynan_pul = Double.parseDouble(girdeyji_pul.getText().toString());
                        umumy_almaly_pul = (satylan_haryt_sany * haryt_bahasy);

                        galan_pul = umumy_almaly_pul - alynan_pul;

                        galan_haryt_sany = Math.round(Math.abs(satylan_haryt_sany - (alynan_pul / haryt_bahasy)));
                       san = 0;


                            san = Double.parseDouble(sann);




                        if (satylan_haryt_sany <= san && satylan_haryt_sany > 0) {

                             AlertDialog.Builder alert = new AlertDialog.Builder(context);
                            Toast.makeText(Main2Activity.this,String.valueOf(satylan_haryt_sany)+":"+String.valueOf(san),Toast.LENGTH_SHORT).show();

                            alert.setTitle("Maglumatlar").setMessage("Sene" + sene + "\nMaşyn: " + s + "\nNokat: " + nokat1 + "\nResminama: " + resminamat
                                    + "\nBaha: " + haryt_bahasy + "\nGirdeyji Mocberi: " + satylan_haryt_sany + "\nÖlçeg birlik: " + olceg_bir + "\nGirdeyji pul: " + alynan_pul + "\nUmumy alynmaly pul: " + umumy_almaly_pul
                                    + "\nGalan pul: " + galan_pul + "\nGalan haryt sany: " + galan_haryt_sany).setCancelable(false).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();

                            boolean isInserted = mydb.insertData(sene, car, positions, resmi, olceg_bir, baha.getText().toString(), girdeyji_mocberi.getText().toString(),
                                    girdeyji_pul.getText().toString(), String.valueOf(galan_haryt_sany), String.valueOf(galan_pul));
                            if (isInserted == true) {
                                Toast.makeText(Main2Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                double l=san-satylan_haryt_sany;

                                mydb2.updateData(tb,String.valueOf(l));
                                if(l==0.0)
                                    mydb2.deleteData(tb);
                                arrayList1=getCustomList1();
                                Custom_Resminama_Adapter adapter2=new Custom_Resminama_Adapter(context,arrayList1);
                                if (resminamaspinner!=null) {
                                    resminamaspinner.setAdapter(adapter2);

                                }

                                Cursor res10 = mydb.getAllData();
                                galan=0;
                                while (res10.moveToNext()) {
                                    if(nokat1.equals(res10.getString(3)))
                                        galan+=Double.parseDouble(res10.getString(10));
                                }
                                alert.setTitle("Galyndy:");
                                alert.setMessage(nokat1+" nokadyndy umumy galyndy pul: "+String.valueOf(galan));
                                alert.show();
                            }
                            else
                                Toast.makeText(Main2Activity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context, "Siz artykmaç möçber girizdiňiz!!!", Toast.LENGTH_SHORT).show();
                        }
                    }


                    else{
                        Toast.makeText(context, "Siz artykmaç pul aldyňyz", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Main2Activity.this,String.valueOf(satylan_haryt_sany)+":"+String.valueOf(san),Toast.LENGTH_SHORT).show();


                    }
                    Toast.makeText(Main2Activity.this,String.valueOf(satylan_haryt_sany)+":"+String.valueOf(san),Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    Toast.makeText(context,"Your phone sad "+e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    public void ishle(){
        mashyn=(TextView)findViewById(R.id.mashyn);
        baha=(TextView) findViewById(R.id.baha);
        girdeyji_mocberi=(EditText)findViewById(R.id.girdeyji_mpcberi);
        girdeyji_pul=(EditText)findViewById(R.id.girdeyji_pul);
        hasaba_al=(ImageButton) findViewById(R.id.hasaba_al);
        clearall=(ImageButton) findViewById(R.id.clear);
    }
    private ArrayList<CustomItem> getCustomList() {
        arrayList=new ArrayList<>();
        Cursor res1 = mydb1.getAllData();
        while (res1.moveToNext()) {
            arrayList.add(new CustomItem(res1.getString(1),R.drawable.nokat));
        }
        return arrayList;
    }

    private ArrayList<CustomResminama> getCustomList1() {
        arrayList1=new ArrayList<>();
        Cursor res2 = mydb2.getAllData();
        while (res2.moveToNext()) {
            arrayList1.add(new CustomResminama(res2.getString(1),R.drawable.image36,res2.getString(2),res2.getString(3),res2.getString(0)));
        }
        return arrayList1;
    }

    private ArrayList<Custom_Olceg_birlik> getCustomList2() {
        arrayList2=new ArrayList<>();
        Cursor res2 = olcegBirlikDb.getAllData();
        while (res2.moveToNext()) {
            arrayList2.add(new Custom_Olceg_birlik(res2.getString(1),R.drawable.olceg_birlik));
        }
        return arrayList2;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            CustomItem item=(CustomItem) arrayList.get(position);
            nokat1=item.getNokat();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
