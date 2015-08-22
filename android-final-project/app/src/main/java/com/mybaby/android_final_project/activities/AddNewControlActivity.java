package com.mybaby.android_final_project.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.mybaby.android_final_project.R;
import com.mybaby.android_final_project.backend.PediatricControlDatabaseHelper;
import com.mybaby.android_final_project.model.Control;

import java.util.Calendar;
import java.util.List;

/**
 * Created by SabrinaKay on 08/08/2015.
 */
public class AddNewControlActivity extends Activity {

    EditText controlDateET, controlSizeET, controlWeightET, controlHeadCircumET, controlNoteET, controlPediatricET;
    Spinner controlTeethSpinner;
    Calendar myCalendar = Calendar.getInstance();
    private RadioButton moodHappy, moodCry, moodIndifferent, moodAngry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_control);
        initUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_control, menu);
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

    private void initUI() {

        controlSizeET = (EditText)findViewById(R.id.add_size);
        controlWeightET = (EditText)findViewById(R.id.add_weight);
        controlHeadCircumET = (EditText)findViewById(R.id.add_head_circum);
        controlPediatricET = (EditText)findViewById(R.id.add_pediatric);
        controlNoteET = (EditText)findViewById(R.id.add_note);
        moodHappy = (RadioButton)findViewById(R.id.rb_mood_happy);
        moodAngry = (RadioButton)findViewById(R.id.rb_mood_angry);
        moodIndifferent = (RadioButton)findViewById(R.id.rb_mood_indifferent);
        moodCry = (RadioButton)findViewById(R.id.rb_mood_cry);


        initializeSpinnerTeeth();
        initializeDatePickerDialog();

    }
    private void initializeSpinnerTeeth() {

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.count_teeth, android.R.layout.simple_spinner_item);
        controlTeethSpinner = (Spinner)findViewById(R.id.spinner_teeth);

        controlTeethSpinner.setAdapter(staticAdapter);

    }

    private void initializeDatePickerDialog() {

        controlDateET = (EditText)findViewById(R.id.editText);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        controlDateET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddNewControlActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {

        controlDateET.setText(PediatricControlDatabaseHelper.dateFormat.format(myCalendar.getTime()));
    }

    public void saveNewControl(View v) {

        if (checkDataInput()) {

            String mood = "";

            if (moodAngry.isChecked()==true) {
                mood = moodAngry.getText().toString();
            } else if (moodHappy.isChecked()==true) {
                mood = moodHappy.getText().toString();
            } else if (moodCry.isChecked()==true) {
                mood = moodCry.getText().toString();
            } else if (moodIndifferent.isChecked()==true) {
                mood = moodIndifferent.getText().toString();
            }

            String date = controlDateET.getText().toString();
            int patient = 1;
            float weight = Float.parseFloat(controlWeightET.getText().toString());
            float size = Float.parseFloat(controlSizeET.getText().toString());
            float head = Float.parseFloat(controlHeadCircumET.getText().toString());
            int teeth = Integer.parseInt(controlTeethSpinner.getSelectedItem().toString());
            String note = controlNoteET.getText().toString();
            String pediatric = controlPediatricET.getText().toString();

            PediatricControlDatabaseHelper.getDatabaseInstance(this).insertControl(date, patient, weight, size, head, teeth, pediatric, note, mood);

            Toast.makeText(this,R.string.message_new_control_added,Toast.LENGTH_LONG).show();
            finish();

        }
        else {
            Toast.makeText(this,R.string.error_message_new_control,Toast.LENGTH_LONG).show();
        }


    }

    private boolean checkDataInput() {

        return controlDateET.getText().length() > 0 &&
                controlSizeET.getText().length() > 0 &&
                controlWeightET.getText().length() > 0 &&
                controlHeadCircumET.getText().length() > 0 &&
                controlNoteET.getText().length() > 0 &&
                controlPediatricET.getText().length() > 0;

    }
}
