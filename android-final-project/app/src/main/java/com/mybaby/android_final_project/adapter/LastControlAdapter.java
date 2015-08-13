package com.mybaby.android_final_project.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.model.Control;

import java.util.ArrayList;

/**
 * Created by Paula on 10/08/2015.
 */
public class LastControlAdapter extends BaseAdapter {

    Context ctx;
    ControlDAOImpl control;
    LayoutInflater inflater;


    public LastControlAdapter(Context ctx,ControlDAOImpl control){
        super();
        this.ctx= ctx;
        this.control = control;
        this.inflater = ((Activity) ctx).getLayoutInflater();
    }

    @Override
       public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int index)
    {
        return this.control.getControl(index);
    }

    @Override
    public long getItemId(int index) {
        return 0;
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {
      /*  view = View.inflate(ctx, R.layout.view_adapter, null);
        TextView noticia = (TextView) view.findViewById(R.id.contenido);
        control.setText(control.get(index).getTitulo());
        Log.d("LOG", "Indice:" + index);
        return view;*/
        return null;
    }

    public void deleteItem(int index)
    {
        this.control.deleteControl(index);
    }

}
