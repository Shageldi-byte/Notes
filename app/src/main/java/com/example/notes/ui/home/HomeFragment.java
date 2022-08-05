package com.example.notes.ui.home;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.notes.DataBaseHelper;
import com.example.notes.NokatDb;
import com.example.notes.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    NokatDb mydb;
    Context context;
    ListView listView;
    List<String> arrayList=new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = container.getContext();
        listView=(ListView) root.findViewById(R.id.lv);
        //final TextView textView = root.findViewById(R.id.text_home);
        mydb = new NokatDb(context);
        Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found");

        }
        while (res.moveToNext()) {
              arrayList.add(res.getString(0)+". "+res.getString(1));
        }


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        listView.setAdapter(arrayAdapter);


        return root;
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}