package com.example.notes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Send extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog.Builder alertDialog;
    ProgressDialog progressDialog;
    String n="0";

    Send(Context ctx){
        context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url="http://"+params[11]+"/r.php";
        if(type.equals("login")){
            try {

                String sene=params[1];
                String mashyn=params[2];
                String nokat=params[3];
                String resminama=params[4];
                String olceg_birlik=params[5];
                String baha=params[6];
                String girdeyji_mocberi=params[7];
                String girdeyji_pul=params[8];
                String galyndy_mocberi=params[9];
                String galyndy_pul=params[10];

                URL url=new URL(login_url);
                HttpURLConnection httpsURLConnection= (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream=httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("sene","UTF-8")+"="+URLEncoder.encode(sene,"UTF-8")+"&"
                        +URLEncoder.encode("mashyn","UTF-8")+"="+URLEncoder.encode(mashyn,"UTF-8")
                        +"&"
                        +URLEncoder.encode("nokat","UTF-8")+"="+URLEncoder.encode(nokat,"UTF-8")
                        +"&"
                        +URLEncoder.encode("resminama","UTF-8")+"="+URLEncoder.encode(resminama,"UTF-8")
                        +"&"
                        +URLEncoder.encode("olceg_birlik","UTF-8")+"="+URLEncoder.encode(olceg_birlik,"UTF-8")
                        +"&"
                        +URLEncoder.encode("baha","UTF-8")+"="+URLEncoder.encode(baha,"UTF-8")
                        +"&"
                        +URLEncoder.encode("girdeyji_mocberi","UTF-8")+"="+URLEncoder.encode(girdeyji_mocberi,"UTF-8")
                        +"&"
                        +URLEncoder.encode("girdeyji_pul","UTF-8")+"="+URLEncoder.encode(girdeyji_pul,"UTF-8")
                        +"&"
                        +URLEncoder.encode("galyndy_mocberi","UTF-8")+"="+URLEncoder.encode(galyndy_mocberi,"UTF-8")
                        +"&"
                        +URLEncoder.encode("galyndy_pul","UTF-8")+"="+URLEncoder.encode(galyndy_pul,"UTF-8")
                        ;
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpsURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
//        progressDialog=new ProgressDialog(context);
//        progressDialog.setMessage("Birza garaşyň...");
//        progressDialog.setTitle("Maglumatlar ugradylýar");
//        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
//        progressDialog.dismiss();
         if(result!=null){
             if(result.equals("Ugradyldy")){
                 n="1";
             }
             else {
                 n="0";
             }

         }

        SharedPreferences settings=context.getSharedPreferences("netije",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("n",n);
        editor.commit();



    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
