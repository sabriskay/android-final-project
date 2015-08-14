package com.mybaby.android_final_project.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.model.Control;

import java.util.ArrayList;
import java.util.List;

public class ListControlAdapter extends BaseAdapter {

    Context ctx;
   // ControlDAOImpl control;
    ArrayList<Control> control;
    LayoutInflater inflater;

    public ListControlAdapter(Context ctx ,  ControlDAOImpl controlDAOImpl) {
        super();
        this.ctx= ctx;
        this.control = (ArrayList<Control>) controlDAOImpl.getAllControls();
       // this.inflater = ((Activity) ctx).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int index) {
        return 0;
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {
        // view = View.inflate(ctx, R.layout.tv_content_control_progress, null);
        TextView controlTv = (TextView) view.findViewById(R.id.tv_control);

        controlTv.setText(control.get(index).getPediatrician());
        Log.d("LOG", "Indice:" + index);
        return view;
    }

    public void deleteItem(int index)
    {
        //this.control.deleteControl(index);
    }

}
