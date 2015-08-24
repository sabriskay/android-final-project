package com.mybaby.android_final_project.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.model.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SabrinaKay on 8/17/15.
 */

public class ListControlAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> headerList;
    private HashMap<String, ArrayList<Control>> childList;
    private Control control;

    public ListControlAdapter(Context context, List<Control> controlList) {
        this.context = context;
        buildAdapterLists(controlList);
    }

    private void buildAdapterLists(List<Control> controlList) {
        headerList = new ArrayList<>();
        childList = new HashMap<>();
        ArrayList<Control> tempChildList;

        for (Control control: controlList) {
            String date = PediatricControlDatabaseHelper.getDatabaseInstance(context).convertCalendarToString(control.getDateControl());

            if(!headerList.contains(date)) {
                headerList.add(date);

                tempChildList = new ArrayList<>();
                for (Control otherControl: controlList) {
                    String otherDate = PediatricControlDatabaseHelper.getDatabaseInstance(context).convertCalendarToString(otherControl.getDateControl());
                    if(otherDate.equals(date)) {
                        tempChildList.add(otherControl);
                    }
                }

                childList.put(date, tempChildList);
            }
        }
    }

    @Override
    public int getGroupCount() {
        return headerList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(headerList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(headerList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflaterInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflaterInflater.inflate(R.layout.control_history_adapter_header, null);
        }

        TextView date = (TextView) convertView.findViewById(R.id.tv_date);
        date.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        this.control = (Control) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflaterInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflaterInflater.inflate(R.layout.control_history_adapter_row, null);
        }

        TextView pediatrician = (TextView) convertView.findViewById(R.id.tv_pediatrician_value);
        pediatrician.setText(control.getPediatrician());

        TextView size = (TextView) convertView.findViewById(R.id.tv_size_value);
        size.setText(String.valueOf(control.getHeight()));

        TextView weight = (TextView) convertView.findViewById(R.id.tv_weight_value);
        weight.setText(String.valueOf(control.getWeight()));

        TextView headCircumference = (TextView) convertView.findViewById(R.id.tv_head_circumference_value);
        headCircumference.setText(String.valueOf(control.getHeadCircumference()));

        TextView teethCircumference = (TextView) convertView.findViewById(R.id.tv_teeth_amount_value);
        teethCircumference.setText(String.valueOf(control.getTeethAmount()));

        TextView mood = (TextView) convertView.findViewById(R.id.tv_mood_value);
        mood.setText(control.getMood());

        TextView notes = (TextView) convertView.findViewById(R.id.tv_notes_value);
        notes.setText(control.getNotes());

        //delete
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Control contr = (Control) getChild(groupPosition, childPosition);
                confirmDelete(contr.getIdControl());
                notifyDataSetChanged();
                return true;
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void removeChild(int idControl)
    {

        ControlDAO controlDaoImpl=  new ControlDAOImpl(context);
        controlDaoImpl.deleteControl(idControl);
    }


    private void confirmDelete(final int idControl) throws Resources.NotFoundException
    {
        new AlertDialog.Builder(context)
                .setTitle("Confirm delete control item")
                .setMessage("Do you confirm deletion?")
                .setIcon(
                        context.getResources().getDrawable(
                                android.R.drawable.ic_dialog_alert))
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //Do Something Here
                                Toast.makeText(context, R.string.message_delete_control,Toast.LENGTH_SHORT).show();
                                removeChild(idControl);
                                notifyDataSetChanged();

                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(context, R.string.message_cancel_delete,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
    }

}
