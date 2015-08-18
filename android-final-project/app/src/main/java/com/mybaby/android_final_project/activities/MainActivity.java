package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.PatientDAO;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.dao.impl.PatientDAOImpl;
import com.mybaby.android_final_project.model.Control;
import com.mybaby.android_final_project.model.Patient;

/**
 * Created by SabrinaKay on 08/08/2015.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("estoy", "volviendo");
        initUI();
    }


    private void initUI() {

        ControlDAO controlDAOImpl= new ControlDAOImpl(this);
        Control lastControl = controlDAOImpl.getLastControl();

        PatientDAO patientDAOImpl = new PatientDAOImpl(this);
        Patient patient = patientDAOImpl.getPatient(1);

        String genderPatient = (String)patient.getGenre();

        TextView controlDate = (TextView)findViewById(R.id.last_date);
        TextView controlSize = (TextView)findViewById(R.id.last_size);
        TextView controlWeight = (TextView)findViewById(R.id.last_weight);
        TextView controlHeadC = (TextView)findViewById(R.id.last_head_circum);
        TextView controlNotes = (TextView)findViewById(R.id.last_note);
        ImageView pacientIcon = (ImageView)findViewById(R.id.iconBaby);
        LinearLayout linearBorderIcon = (LinearLayout)findViewById(R.id.linear_border);

        controlDate.setText(PediatricControlDatabaseHelper.getDatabaseInstance(this).convertCalendarToString(lastControl.getDateControl()));
        controlSize.setText(Float.toString(lastControl.getHeight()) + " cm");
        controlWeight.setText(Float.toString(lastControl.getWeight()) + " kg");
        controlHeadC.setText(Float.toString(lastControl.getHeadCircumference()) + " cm");
        controlNotes.setText(lastControl.getNotes());

        if(genderPatient.equalsIgnoreCase("F")) {
            pacientIcon.setBackgroundResource(R.drawable.female109);
            linearBorderIcon.setBackgroundResource(R.drawable.icon_border_fem);

        }
        else {
            pacientIcon.setBackgroundResource(R.drawable.baby63);
            linearBorderIcon.setBackgroundResource(R.drawable.icon_border);
        }
    }

    public void goToViewProfile(View v) {

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goToViewNewControl(View v) {

        Intent intent = new Intent(this, AddNewControlActivity.class);
        startActivity(intent);
    }

    public void goToViewProgress(View v) {

        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }

    public void goToViewControlsHistory(View v) {

        Intent intent = new Intent(this, ControlHistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
