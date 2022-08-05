package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class esasy_menu extends AppCompatActivity {
    private ViewPager2 viewPager2;
    String id = "1", result;
    DataBaseHelper mydb;
    Context context = this;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> bahalar = new ArrayList<>();
    double girdeyji_moc = 0, girdeji_pul = 0, galyndy_moc = 0, galyndy_pul = 0;
    TextView umumy_girdeyji_mocberi, umumy_girdeyji_pul, umumy_galyndy_mocberi, umumy_galyndy_pul, umumy_harytlar;
    PieChart pieChart;
    ArrayList<PieEntry> yValues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esasy_menu);
        LinearLayout nokatlar = findViewById(R.id.nokatlar);
        LinearLayout hasabat_girizmek = findViewById(R.id.hasabat_girizmek);
        LinearLayout hasabaty_gormek = findViewById(R.id.hasabaty_gormek);
        LinearLayout hasabaty_ugratmak = findViewById(R.id.hasabaty_ugratmak);
        LinearLayout hasabatlar_taryhy = findViewById(R.id.hasabatlar_taryhy);
        LinearLayout baza = findViewById(R.id.baza);
        LinearLayout almak = findViewById(R.id.almak_btn);
        LinearLayout about = findViewById(R.id.about);
        pieChart=findViewById(R.id.piechart);
        nokatlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(esasy_menu.this, Nokat_Ac.class));
            }
        });

        hasabat_girizmek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(esasy_menu.this, Main2Activity.class));
            }
        });

        hasabaty_gormek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(esasy_menu.this, Show_Data.class));
            }
        });

        hasabaty_ugratmak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(esasy_menu.this, Send_data.class));
            }
        });

        hasabatlar_taryhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(esasy_menu.this, Hasabatlar_Taryhy.class));
            }
        });

        baza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(esasy_menu.this, bazadaky_harytlar.class));
            }
        });

        almak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(esasy_menu.this, harytlary_almak.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(esasy_menu.this, Programma_Barada.class));
            }
        });


        //yukle();
    }

    private void yukle() {
        mydb = new DataBaseHelper(context);
        final Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            return;
        }
        if (id.equals("1")) {
            while (res.moveToNext()) {
                arrayList.add(res.getString(4));
            }


            HashSet<String> set = new HashSet<String>(arrayList);


            final ArrayList<String> result = new ArrayList<>(set);


            double k = 0;
            for (int i = 0; i < result.size(); i++) {

                final Cursor res1 = mydb.getAllData();
                while (res1.moveToNext()) {

                    if (res1.getString(4).equals(result.get(i))) {
                        k += Double.parseDouble(res1.getString(7));


                    } else {
                        continue;
                    }
//                        if(res1.getString(4).equals("Taze ay")){
//                            k=1;
//                        }


                }
                bahalar.add(String.valueOf(k));
                k = 0;


            }

            String sony = "";
            for (int j = 0; j < result.size(); j++) {
                sony += bahalar.get(j) + " sany " + result.get(j) + "\n";
                yValues.add(new PieEntry(Float.parseFloat(bahalar.get(j)), result.get(j)));
            }


            pieChart.setUsePercentValues(true);
            pieChart.getDescription().setEnabled(false);
            pieChart.setExtraOffsets(5, 10, 5, 5);
            pieChart.setDragDecelerationFrictionCoef(0.95f);
            pieChart.setDrawHoleEnabled(true);
            pieChart.setHoleColor(Color.WHITE);
            pieChart.setTransparentCircleRadius(60f);
            pieChart.animateY(1000, Easing.EasingOption.EaseOutCubic);
            PieDataSet dataSet = new PieDataSet(yValues, "Täze satylan harytlar");
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(5f);
            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

            PieData data = new PieData((dataSet));
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.YELLOW);
            pieChart.setData(data);
        }
    }
}
