package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.model.BloodType;
import com.mybaby.android_final_project.model.Patient;

import java.util.Calendar;

public class ProfileActivity extends Activity {
    private boolean firstTime;
    EditText birthdateET, fullNameET, iDET;
    RadioButton genderFemRb, genderMaleRb;
    Calendar myCalendar = Calendar.getInstance();
    Spinner bloodSpinner;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        firstTime = getIntent().getBooleanExtra("firstTime", false);

        initUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);
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

    @Override
    public void onBackPressed() {
        if(firstTime) return;

        super.onBackPressed();
    }

    private void initUI() {

        patient = PediatricControlDatabaseHelper.getDatabaseInstance(this).getCurrentPatient();

        fullNameET = (EditText)findViewById(R.id.full_name);
        iDET = (EditText)findViewById(R.id.baby_id);
        genderFemRb = (RadioButton)findViewById(R.id.rb_gender_fem);
        genderMaleRb = (RadioButton)findViewById(R.id.rb_gender_male);

        initializeDatePickerDialog();
        initializeSpinnerBlood();

        if (patient != null) {

            fullNameET.setText(patient.getName());
            iDET.setText(String.valueOf(patient.getid()));
            if (patient.getGenre().equalsIgnoreCase("F")) {
                genderFemRb.setChecked(true);
            } else {
                genderMaleRb.setChecked(true);
            }

            birthdateET.setText(PediatricControlDatabaseHelper.getDatabaseInstance(this).convertCalendarToString(patient.getBirthDate()));

            bloodSpinner.setSelection(patient.getIdBloodGroup() - 1);
        }

    }

    private void initializeDatePickerDialog() {

        birthdateET = (EditText)findViewById(R.id.editText);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        birthdateET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent m) {
                // TODO Auto-generated method stub
                switch (m.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        new DatePickerDialog(ProfileActivity.this, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }

                return true;
            }
        });
    }

    private void updateLabel() {
        birthdateET.setText(PediatricControlDatabaseHelper.dateFormat.format(myCalendar.getTime()));
    }

    private void initializeSpinnerBlood() {
        bloodSpinner = (Spinner)findViewById(R.id.spinner_blood_type);

        ArrayAdapter<BloodType> spinnerArrayAdapter = new ArrayAdapter<BloodType>(this, android.R.layout.simple_spinner_item, PediatricControlDatabaseHelper.getDatabaseInstance(this).getAllBloodType());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodSpinner.setAdapter(spinnerArrayAdapter);

    }

    public void saveBaby(View v) {

        if (checkDataInput())
        {
            String gender;

            if (genderMaleRb.isChecked()) {
                gender = "M";
            }
            else {
                gender = "F";
            }

            BloodType selectBloodType = (BloodType)bloodSpinner.getSelectedItem();

            Calendar birthdate = PediatricControlDatabaseHelper.getDatabaseInstance(this).convertStringToCalendar(birthdateET.getText().toString());
            int groupFactor = selectBloodType.getId();
            String name = fullNameET.getText().toString();
            int id = Integer.parseInt(iDET.getText().toString());

            if (!firstTime) {
                patient.setGenre(gender);
                patient.setName(name);
                patient.setBirthDate(birthdate);
                patient.setId(id);
                patient.setIdBloodGroup(groupFactor);

                PediatricControlDatabaseHelper.getDatabaseInstance(this).updatePatient(patient);
                Toast.makeText(this, R.string.message_edit_patient, Toast.LENGTH_LONG).show();
            }
            else {
                PediatricControlDatabaseHelper.getDatabaseInstance(this).insertPatient(name, birthdate, id, gender, groupFactor);
                Toast.makeText(this, R.string.message_add_patient, Toast.LENGTH_LONG).show();

            }

            finish();

        }
        else {
            Toast.makeText(this,R.string.error_message_empty_fields,Toast.LENGTH_LONG).show();
        }

    }

    private boolean checkDataInput() {

        return birthdateET.getText().length() > 0 &&
                (genderFemRb.isChecked() || genderMaleRb.isChecked()) &&
                fullNameET.getText().length() > 0 &&
                iDET.getText().length() > 0;

    }
}
