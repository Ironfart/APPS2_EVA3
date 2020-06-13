package com.example.eva3_1_conexionmysql;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ActorAdapter extends ArrayAdapter<JSONObject> {

    Context context;

    int resource;

    List<JSONObject> objects;

    public ActorAdapter(@NonNull Context context, int resource, @NonNull List<JSONObject> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            convertView = layoutInflater.inflate(resource, parent, false);
        }
        TextView txtName, txtLastN;
        txtName = convertView.findViewById(R.id.txtVwName);
        txtLastN = convertView.findViewById(R.id.txtVwLastN);

        //llenar datos de la lista
        try {
            txtName.setText(objects.get(position).getString("first_name"));
            txtLastN.setText(objects.get(position).getString("last_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
