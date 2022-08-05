package com.example.notes.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.notes.Main2Activity;
import com.example.notes.NokatDb;
import com.example.notes.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    Button goshbtn;
    EditText nokatgiriz;
    NokatDb mydb;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        goshbtn=(Button) root.findViewById(R.id.goshbtn);
        context=container.getContext();
        nokatgiriz=root.findViewById(R.id.nokatgiriz);
        mydb = new NokatDb(context);
        goshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertData(nokatgiriz.getText().toString());
                if (isInserted == true)
                    Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "Data not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}