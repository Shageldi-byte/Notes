package com.example.notes.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.notes.Change_Data;
import com.example.notes.NokatDb;
import com.example.notes.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    Context context;
    EditText edtid;
    ImageButton update,delete;
    NokatDb mydb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        context=container.getContext();
        update=(ImageButton) root.findViewById(R.id.update_nokat);
        delete=(ImageButton) root.findViewById(R.id.delete_nokat);
        edtid=(EditText) root.findViewById(R.id.nokat_id);
        mydb=new NokatDb(context);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deletedrows=mydb.deleteData(edtid.getText().toString());
                if(deletedrows>0) Toast.makeText(context,"Maglumat Pozuldy",Toast.LENGTH_SHORT).show();
                else   Toast.makeText(context,"Ýalňyşlyk ýüze çykdy!",Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }
}