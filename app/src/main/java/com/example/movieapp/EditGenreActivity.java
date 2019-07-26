package com.example.movieapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.model.entity.Genre;
import com.example.movieapp.model.repo.GenreRepo;
import com.google.android.material.textfield.TextInputEditText;

public class EditGenreActivity extends AppCompatActivity {

    public static final String KEY_GENRE_ID = "genre_id";

    private TextInputEditText edGenre;
    private Button btnSave;
    private Button btnDelete;

    private Genre genre;
    private GenreRepo repo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_genre);

        repo = new GenreRepo(MainApplication.getDatabase(this).genreDao());

        int id = getIntent().getIntExtra(KEY_GENRE_ID, 0);

        edGenre = findViewById(R.id.edGenreType);
        btnSave = findViewById(R.id.btnGenreSave);
        btnDelete = findViewById(R.id.btnGenreDelete);

        if (id > 0) {
            genre = repo.getGenre(id);
            btnDelete.setVisibility(View.VISIBLE);

            edGenre.setText(genre.getGenre());
        } else {
            genre = new Genre();
        }

    }

    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.btnGenreSave:
                genre.setGenre(edGenre.getText().toString());
                repo.save(genre);
                break;
            case R.id.btnGenreDelete:
                repo.delete(genre);
                break;
        }

        finish();
    }
}
