package com.example.eva3_2_conexionmysqlvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstActors;
    ArrayList<JSONObject> listJson = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstActors = findViewById(R.id.lstVwActors);

        /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "http://192.168.1.75:3000/actors",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = response.getJSONObject(i);
                                listJson.add(jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lstActors.setAdapter(new ActorAdapter(MainActivity.this, R.layout.layout_actor, listJson));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(this).add(jsonArrayRequest);*/

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("first_name","John");
            jsonObject.put("last_name","Smith");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                "http://192.168.1.75:3000/actors",
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
