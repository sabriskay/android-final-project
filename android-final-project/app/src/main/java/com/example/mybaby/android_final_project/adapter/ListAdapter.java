package com.example.mybaby.android_final_project.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mybaby.android_final_project.model.Control;

import java.util.ArrayList;

/**
 * Created by Paula on 10/08/2015.
 */
public class ListAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<Control> control;
    LayoutInflater inflater;


    public ListAdapter(Context ctx, ArrayList<Control> array){
        super();
        this.ctx= ctx;
        this.control = array;
        this.inflater = ((Activity) ctx).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return this.control.size();
    }

    @Override
    public Object getItem(int index)
    {
        return this.control.get(index);
    }

    @Override
    public long getItemId(int index) {
        return 0;
    }

    @Override
    public View getView(int index, View view, ViewGroup parent)
    {
       // view = View.inflate(ctx, R.layout.view_adapter, null);
    //   TextView noticia = (TextView) view.findViewById(R.id.contenido);
       // control.setText(control.get(index).getTitulo());
        Log.d("LOG", "Indice:" + index);
        return view;
    }

    public void deleteItem(int index)
    {
        this.control.remove(index);
    }

}
