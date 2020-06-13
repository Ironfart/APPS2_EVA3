package com.example.eva3_1_conexionmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstActors;

    ArrayList<JSONObject> jsonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstActors = findViewById(R.id.lstActors);

        //new ConnClass().execute("http://192.168.1.75:3000/actors");

        new OtherConnClass().execute("http://192.168.1.75:3000/actors/202");
    }

    class ConnClass extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String sUrl = strings[0];
            String res = null;
            try {
                URL url = new URL(sUrl);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();

                if (http.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader isr  = new InputStreamReader(http.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    res = br.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s != null){
                try {
                    JSONArray jsonArray = new JSONArray(s);

                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        jsonList.add(jsonObject);
                    }
                    lstActors.setAdapter(new ActorAdapter(MainActivity.this, R.layout.layout_actors, jsonList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    class OtherConnClass extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String sUrl = strings[0];
            String res = null;
            try {
                URL url = new URL(sUrl);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                //http.setRequestMethod("POST");
                //http.setRequestMethod("PUT");
                http.setRequestMethod("DELETE"); // para borrar no se necesitan los jsonobject ni dataoutput stream
                http.setDoInput(true);
                http.setDoOutput(true);
                http.setRequestProperty("Content-Type","application/json;charset=utf-8");
                http.connect();

                /*JSONObject jsonObject = new JSONObject();
                jsonObject.put("first_name","John");
                jsonObject.put("last_name","Smith Turkey");

                DataOutputStream dos = new DataOutputStream(http.getOutputStream());
                dos.write(jsonObject.toString().getBytes());
                dos.flush();
                dos.close();*/

                InputStreamReader isr  = new InputStreamReader(http.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                res = br.readLine();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } /*catch (JSONException e) {
                e.printStackTrace();
            }*/
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
