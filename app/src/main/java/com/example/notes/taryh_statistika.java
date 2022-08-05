package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashSet;

public class taryh_statistika extends AppCompatActivity {
    String id,result;
    history_of_report_db mydb;
    Context context=this;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> bahalar=new ArrayList<>();
    double girdeyji_moc=0,girdeji_pul=0,galyndy_moc=0,galyndy_pul=0;
    TextView umumy_girdeyji_mocberi,umumy_girdeyji_pul,umumy_galyndy_mocberi,umumy_galyndy_pul,umumy_harytlar;
    PieChart pieChart;
    ArrayList<PieEntry> yValues=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taryh_statistika);
        umumy_girdeyji_mocberi=findViewById(R.id.umumy_girdeyji_mocberi);
        umumy_girdeyji_pul=findViewById(R.id.umumy_girdeyji_pul);
        umumy_galyndy_mocberi=findViewById(R.id.umumy_galyndy_mocberi);
        umumy_galyndy_pul=findViewById(R.id.umumy_galyndy_pul);
        umumy_harytlar=findViewById(R.id.umumy_harytlar);
        pieChart=findViewById(R.id.piechart);
        Intent intent=getIntent();
        mydb=new history_of_report_db(this);
        id=intent.getStringExtra("id");
        result=intent.getStringExtra("result");
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found");
            return;
        }
        if(id.equals("1")){
            while (res.moveToNext()) {
                girdeyji_moc+=Double.parseDouble(res.getString(7));
                girdeji_pul+=Double.parseDouble(res.getString(8));
                galyndy_moc+=Double.parseDouble(res.getString(9));
                galyndy_pul+=Double.parseDouble(res.getString(10));
                arrayList.add(res.getString(4));
            }
            umumy_girdeyji_mocberi.setText("Umumy girdeýji möçberi: "+girdeyji_moc+" sany karopka");
            umumy_girdeyji_pul.setText("Umumy girdeýji pul: "+girdeji_pul+" manat");
            umumy_galyndy_mocberi.setText("Umumy galyndy möçberi: "+galyndy_moc+" sany karopka");
            umumy_galyndy_pul.setText("Umumy galyndy pul: "+galyndy_pul+" manat");

            HashSet<String> set = new HashSet<String>(arrayList);


            final ArrayList<String> result = new ArrayList<>(set);


            double k=0;
            for(int i=0;i<result.size();i++){

                final Cursor res1 = mydb.getAllData();
                while (res1.moveToNext()) {

                        if(res1.getString(4).equals(result.get(i))){
                            k+=Double.parseDouble(res1.getString(7));


                        }
                        else{
                            continue;
                        }
//                        if(res1.getString(4).equals("Taze ay")){
//                            k=1;
//                        }


                }
                bahalar.add(String.valueOf(k));
                k=0;


            }

            String sony="";
            for(int j=0;j<result.size();j++){
                sony+=bahalar.get(j)+" sany "+result.get(j)+"\n";
                yValues.add(new PieEntry(Float.parseFloat(bahalar.get(j)),result.get(j)));
            }

            umumy_harytlar.setText("Umumy satylan harytlar: \n"+sony);
            pieChart.setUsePercentValues(true);
            pieChart.getDescription().setEnabled(false);
            pieChart.setExtraOffsets(5,10,5,5);
            pieChart.setDragDecelerationFrictionCoef(0.95f);
            pieChart.setDrawHoleEnabled(true);
            pieChart.setHoleColor(Color.WHITE);
            pieChart.setTransparentCircleRadius(60f);
            pieChart.animateY(1000, Easing.EasingOption.EaseOutCubic);
            PieDataSet dataSet=new PieDataSet(yValues,"Umumy satylan harytlar");
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(5f);
            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

            PieData data=new PieData((dataSet));
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.YELLOW);
            pieChart.setData(data);






        }
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
