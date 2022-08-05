package com.example.notes;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Profile_Parser extends AsyncTask<Void,Integer,Integer> {
    harytlardb harytlardb;
    Context c;
    String data;

    List<String> d=new ArrayList<>();
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> players=new ArrayList<>();
    ArrayList<harytlar_class> airports=new ArrayList<>();
    ArrayList<String> passwords=new ArrayList<>();
    ProgressDialog pd;

    public Profile_Parser(Context c, String data) {
        this.c = c;
        this.data = data;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

//        pd=new ProgressDialog(c);
//        pd.setTitle("Maglumatlar goşulýar!!!");
//        pd.setMessage("Maglumatlar goşulýar...");
//        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1)
        {

//            Custom_Profile custom_profile=new Custom_Profile(airports,c);
//            lv.setAdapter(custom_profile);
            Toast.makeText(c,"Maglumatlar üstünlükli kabul edildi!",Toast.LENGTH_LONG).show();












        }else
        {
            Toast.makeText(c,"Ýalňyşlyk ýüze çykdy!!!",Toast.LENGTH_SHORT).show();
        }
        if(airports.toString().equals("[]")){

        }

//        pd.dismiss();
    }
    public void startact(){

    }

    private int parse(){
        try{
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;
            boolean ugrat=false;
            harytlardb=new harytlardb(c);

            airports.clear();

            for(int i=ja.length()-1;i>=0;i--){

                jo=ja.getJSONObject(i);


//                        airports.add(new harytlar_class(jo.getString("id"),jo.getString("haryt_ady"),jo.getString("bahasy"),
//                                jo.getString("sany"),String.valueOf()));
                     Cursor cursor=harytlardb.getSelect(jo.getString("haryt_ady"));
               if (cursor.getCount() == 0) {
                    ugrat=harytlardb.insertData(jo.getString("haryt_ady"),jo.getString("bahasy"),jo.getString("sany"));
                   Log.d("SQLLLLLL: ",ugrat+"");
               }
               else{
                    try {
                        while(cursor.moveToNext()) {
                            double sany = Double.parseDouble(cursor.getString(3)) + Double.parseDouble(jo.getString("sany"));
                            harytlardb.updateData(cursor.getString(0), String.valueOf(sany));
                            Log.d("SQLLLLLL: ", sany + "");
                        }
                    }
                    catch (Exception ignored){
                        Log.d("SQLLLLLL: ",ignored.getMessage()+"");
                    }
                }











            }


//            Toast.makeText(c,"Boldy",Toast.LENGTH_SHORT).show();



            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }




}
