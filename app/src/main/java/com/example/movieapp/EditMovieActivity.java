package com.example.movieapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.model.entity.Genre;
import com.example.movieapp.model.entity.Movie;
import com.example.movieapp.model.entity.MovieCast;
import com.example.movieapp.model.repo.GenreRepo;
import com.example.movieapp.model.repo.MovieCastRepo;
import com.example.movieapp.model.repo.MovieRepo;
import com.google.android.material.textfield.TextInputEditText;

import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.List;

public class EditMovieActivity extends AppCompatActivity {

    public static final String KEY_MOVIE_ID = "movie_id";

    private TextInputEditText edMovie;
    private TextInputEditText edDate;
    private TextInputEditText edDuration;
    private TextInputEditText edCountry;
    private TextInputEditText edActor;
    private TextInputEditText edActress;
    private TextInputEditText edDirector;
    private TextInputEditText edGenre;
    private TextInputEditText edLanguage;
    private Spinner spinnerActor;
    private Spinner spinnerActress;
    private Spinner spinnerGenre;
    private Button btnSave;
    private Button btnDelete;

    private Movie movie = new Movie();
    private MovieRepo repo;
    private MovieCastRepo castRepo;
    private GenreRepo genreRepo;

    private ArrayAdapter<Genre> genreArrayAdapter;
    private ArrayAdapter<MovieCast> movieCastArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        genreArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        repo = new MovieRepo(MainApplication.getDatabase(this).movieDao(), MainApplication.getDatabase(this).genreDao());
        genreRepo = new GenreRepo(MainApplication.getDatabase(this).genreDao());
        castRepo = new MovieCastRepo(MainApplication.getDatabase(this).castDao());

        int id = getIntent().getIntExtra(KEY_MOVIE_ID, 0);

        edMovie = findViewById(R.id.edMovie);
        edDate = findViewById(R.id.edDate);
        edDuration = findViewById(R.id.edDuration);
        edCountry = findViewById(R.id.edCountry);
        edActor = findViewById(R.id.edActor);
        edActress = findViewById(R.id.edActress);
        edDirector = findViewById(R.id.edDirector);
        edGenre = findViewById(R.id.edGenreType);
        edLanguage = findViewById(R.id.edLanguage);
        spinnerActor = findViewById(R.id.spinnerActor);
        spinnerActress = findViewById(R.id.spinnerActress);
        spinnerGenre = findViewById(R.id.spinnerGenre);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        if (id > 0) {
            movie = repo.getMovie(id);
            btnDelete.setVisibility(View.VISIBLE);

            edMovie.setText(movie.getTitle());
            edDuration.setText(movie.getDuration());
            edCountry.setText(movie.getCountry());
            //edActor TODO
            //edActress TODO
            edDirector.setText(movie.getDirector());
            edLanguage.setText(movie.getLanguage());

            Genre genre = genreRepo.getGenre(movie.getGenreId());
            edGenre.setText(genre.getGenre());
            //spinnerActor TODO
            //spinnerActress TODO
            spinnerGenre.setSelection(genre.getId());

        }

        edDate.setText(movie.getReleaseDate().toString("yyyy/MM/dd"));
        edDate.setOnKeyListener((v, keyCode, event) -> false);
        edDate.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                return handleTouch(v);
            }
            return true;
        });

        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edGenre.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edGenre.setOnKeyListener((v, keyCode, event) -> true);
        edGenre.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                spinnerGenre.performClick();
            }
            return true;

        });

        loadCastSpinnerData();//TODO
        loadGenreSpinnerData();

        Button btnEditCast = findViewById(R.id.btnAddNewCast);
        Button btnEditGenre = findViewById(R.id.btnAddNewGenre);

        btnEditCast.setOnClickListener(v -> {
            Intent intent = new Intent(EditMovieActivity.this, EditCastActivity.class);
            startActivity(intent);
        });

        btnEditGenre.setOnClickListener(v -> {
            Intent intent = new Intent(EditMovieActivity.this, EditGenreActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //cast update
        loadGenreSpinnerData();
    }

    private boolean handleTouch(View v) {
        switch (v.getId()) {
            case R.id.edDate:
                DatePickerDialog datePicker = new DatePickerDialog(this, (dp, year, month, dayOfMonth) -> {
                    TextInputEditText edDate = findViewById(R.id.edDate);
                    Calendar cal = Calendar.getInstance();
                    cal.set(year, month, dayOfMonth);
                    edDate.setText(LocalDate.fromCalendarFields(cal).toString("yyyy/MM/dd"));

                }, movie.getReleaseDate().getYear(), movie.getReleaseDate().getMonthOfYear(), movie.getReleaseDate().getDayOfMonth());

                datePicker.show();
                break;
        }
        return true;
    }

    private void loadGenreSpinnerData() {
        genreArrayAdapter.clear();
        spinnerGenre.setAdapter(genreArrayAdapter);
        genreArrayAdapter.addAll(genreRepo.findAll());
    }

    private void loadCastSpinnerData() {
        //TODO
    }

    public void onMovieBtnClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                movie.setTitle(edMovie.getText().toString());
                movie.setReleaseDate(edDate.getText().toString());
                movie.setDuration(edDuration.getText().toString());
                movie.setCountry(edCountry.getText().toString());
                movie.setDirector(edDirector.getText().toString());
                movie.setLanguage(edLanguage.getText().toString());
                //actor TODO
                //actress TODO

                Genre genre = genreArrayAdapter.getItem(spinnerGenre.getSelectedItemPosition());
                movie.setGenreId(genre.getId());
                repo.save(movie);
                break;
            case R.id.btnDelete:
                repo.delete(movie);
                break;
        }
        finish();
    }
}
