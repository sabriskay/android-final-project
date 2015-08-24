package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.adapter.ListControlAdapter;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;

/**
 * Created by SabrinaKay on 08/08/2015.
 */
public class ControlHistoryActivity extends Activity {
    ExpandableListView myList;
    ListControlAdapter listControlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_history);

        myList = (ExpandableListView) findViewById(R.id.lv_control_history);

        ControlDAOImpl controlDAOImpl = new ControlDAOImpl(this);

        listControlAdapter = new ListControlAdapter(this, controlDAOImpl.getAllControls());

        myList.setAdapter(listControlAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
        }

        return false;
    }
}
