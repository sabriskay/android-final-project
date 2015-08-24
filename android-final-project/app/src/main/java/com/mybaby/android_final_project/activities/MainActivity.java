package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.dao.ControlDAO;
import com.mybaby.android_final_project.dao.impl.ControlDAOImpl;
import com.mybaby.android_final_project.model.Control;
import com.mybaby.android_final_project.model.Patient;

/**
 * Created by SabrinaKay on 08/08/2015.
 */
public class MainActivity extends Activity {
    private boolean existCurrentPatient = false;
    String genderPatient = null;
    Patient patient ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        PediatricControlDatabaseHelper.getDatabaseInstance(this).setCurrentPatient(PediatricControlDatabaseHelper.getDatabaseInstance(this).getPatient());
        existCurrentPatient = PediatricControlDatabaseHelper.getDatabaseInstance(this).getCurrentPatient() != null;

        if(existCurrentPatient) {
            initUI();
        } else {
            goToViewProfile(null);
        }
    }

    private void initUI() {

        patient = PediatricControlDatabaseHelper.getDatabaseInstance(this).getCurrentPatient();
        ControlDAO controlDAOImpl= new ControlDAOImpl(this);
        Control lastControl = controlDAOImpl.getLastControl();

        genderPatient = patient.getGenre();


        ImageView pacientIcon = (ImageView)findViewById(R.id.iconBaby);
        LinearLayout linearBorderIcon = (LinearLayout)findViewById(R.id.linear_border);
        TextView controlNotes = (TextView) findViewById(R.id.last_note);
        TextView controlDate = (TextView) findViewById(R.id.last_date);
        TextView controlSize = (TextView) findViewById(R.id.last_length);
        TextView controlWeight = (TextView) findViewById(R.id.last_weight);
        TextView controlHeadC = (TextView) findViewById(R.id.last_head_circum);
        if (lastControl != null) {
            controlDate.setText(PediatricControlDatabaseHelper.getDatabaseInstance(this).convertCalendarToString(lastControl.getDateControl()));
            controlSize.setText(String.format("%.2f", lastControl.getHeight()));
            controlWeight.setText(String.format("%.2f", lastControl.getWeight()));
            controlHeadC.setText(String.format("%.2f", lastControl.getHeadCircumference()));
            controlNotes.setText(lastControl.getNotes());
        } else {

            controlDate.setText("");
            controlSize.setText("");
            controlWeight.setText("");
            controlHeadC.setText("");
            controlNotes.setText("");
        }

        if (genderPatient.equalsIgnoreCase("F")) {
            pacientIcon.setBackgroundResource(R.drawable.female109);
            linearBorderIcon.setBackgroundResource(R.drawable.icon_border_fem);
            controlNotes.setBackgroundResource(R.drawable.icon_border_fem);

        } else {
            pacientIcon.setBackgroundResource(R.drawable.baby63);
            linearBorderIcon.setBackgroundResource(R.drawable.icon_border);
            controlNotes.setBackgroundResource(R.drawable.icon_border);
        }
    }

    public void goToViewProfile(View v) {

        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("firstTime", !existCurrentPatient);
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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
