package com.example.movieapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.model.dao.MovieCastDao;
import com.example.movieapp.model.entity.MovieCast;
import com.example.movieapp.model.repo.MovieCastRepo;
import com.google.android.material.textfield.TextInputEditText;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.Calendar;

public class EditCastActivity extends AppCompatActivity {

    public static final String KEY_CAST_ID = "movie_cast_id";

    private TextInputEditText edCastName;
    private TextInputEditText edAge;
    private TextInputEditText edDob;
    private TextInputEditText edOccupation;
    private Spinner spinnerOccupation;
    private Button btnSave;
    private Button btnDelete;

    private MovieCast movieCast = new MovieCast();
    private MovieCastRepo repo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cast);

        repo = new MovieCastRepo(MainApplication.getDatabase(this).castDao());

        int id = getIntent().getIntExtra(KEY_CAST_ID, 0);

        edCastName = findViewById(R.id.edCastName);
        edAge = findViewById(R.id.edAge);
        edDob = findViewById(R.id.edDOB);
        edOccupation = findViewById(R.id.edOccupation);
        spinnerOccupation = findViewById(R.id.spinnerOccupation);
        btnSave = findViewById(R.id.btnCastSave);
        btnDelete = findViewById(R.id.btnCastDelete);

        if (id > 0) {
            movieCast = repo.getMovieCast(id);
            btnDelete.setVisibility(View.VISIBLE);

            edCastName.setText(movieCast.getName());
            edAge.setText(String.valueOf(movieCast.getAge()));
            edOccupation.setText(movieCast.getOccupation().name());
            spinnerOccupation.setSelection(movieCast.getOccupation().ordinal());

        }

        edDob.setText(movieCast.getDateOfBirth().toString("yyyy/MM/dd"));
        edDob.setOnKeyListener((v, keyCode, event) -> false);
        edDob.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                return handleTouch(v);
            }
            return true;
        });

        spinnerOccupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MovieCast.Occupation occupation = MovieCast.Occupation.values()[position];
                edOccupation.setText(occupation.name());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edOccupation.setOnKeyListener((v, keyCode, event) -> true);
        edOccupation.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                spinnerOccupation.performClick();
            }
            return true;
        });


    }

    public void onBtnClick(View v) {
        switch (v.getId()) {
            case R.id.btnCastSave:
                movieCast.setName(edCastName.getText().toString());
                movieCast.setAge(Integer.parseInt(edAge.getText().toString()));
                movieCast.setDateOfBirth(edDob.getText().toString());
                movieCast.setOccupation(MovieCast.Occupation.values()[spinnerOccupation.getSelectedItemPosition()]);
                repo.save(movieCast);
                break;
            case R.id.btnCastDelete:
                repo.delete(movieCast);
                break;
        }
        finish();
    }

    private boolean handleTouch(View v) {
        switch (v.getId()) {
            case R.id.edDOB:
                DatePickerDialog datePicker = new DatePickerDialog(this, (dp, year, month, dayOfMonth) -> {
                    TextInputEditText edDOB = findViewById(R.id.edDOB);
                    Calendar cal = Calendar.getInstance();
                    cal.set(year, month, dayOfMonth);
                    edDOB.setText(LocalDate.fromCalendarFields(cal).toString("yyyy/MM/dd"));

                }, movieCast.getDateOfBirth().getYear(), movieCast.getDateOfBirth().getMonthOfYear(), movieCast.getDateOfBirth().getDayOfMonth());

                datePicker.show();
                break;
        }
        return true;
    }
}
