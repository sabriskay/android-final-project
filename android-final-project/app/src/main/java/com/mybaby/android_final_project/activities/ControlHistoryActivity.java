package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.adapter.ListControlAdapter;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;

public class ControlHistoryActivity extends Activity {


    ListView myList;
    ListControlAdapter listControlAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_history);

        PediatricControlDatabaseHelper.getDatabaseInstance(this).deleteTables();
        PediatricControlDatabaseHelper.getDatabaseInstance(this).onInitializeDB();
        PediatricControlDatabaseHelper.getDatabaseInstance(this).getAllMood();


        myList= (ListView) findViewById(R.id.lv_control_history);

        ControlDAOImpl controlDAOImpl= new ControlDAOImpl(this);

        listControlAdapter = new ListControlAdapter(this, controlDAOImpl);

        myList.setAdapter(listControlAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
