package com.example.notes;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class app extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ArrayList<String> menuitem=new ArrayList<>();
        menuitem.add("Maglumatlary görmek");
        menuitem.add("Maglumatlary Üýtgetmek");
        menuitem.add("Programma Barada");
        for(String t:menuitem){
            MenuItem menuItem1=menu.add(t);
            menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getTitle().toString()=="Maglumatlary görmek"){
                        startActivity(new Intent(app.this,Show_Data.class));
                    }if(item.getTitle().toString()=="Maglumatlary Üýtgetmek"){
                        startActivity(new Intent(app.this,Change_Data.class));
                    }
                    return true;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }
}
