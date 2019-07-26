package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.adapter.MovieAdapter;
import com.example.movieapp.model.entity.Movie;
import com.example.movieapp.model.repo.MovieRepo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovieActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private MovieRepo movieRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieAdapter = new MovieAdapter();
        movieRepo = new MovieRepo(MainApplication.getDatabase(this).movieDao(), MainApplication.getDatabase(this).genreDao());

        RecyclerView recyclerView = findViewById(R.id.movieRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.setOnAdapterItemClickListener(new MovieAdapter.OnAdapterItemClickListener() {
            @Override
            public void onClick(Movie movie) {
                Intent intent = new Intent(MovieActivity.this, EditMovieActivity.class);
                intent.putExtra(EditMovieActivity.KEY_MOVIE_ID, movie.getId());
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabMovie);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, EditMovieActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieAdapter.submitList(movieRepo.findAll());
    }
}
