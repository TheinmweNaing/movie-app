package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.adapter.GenreAdapter;
import com.example.movieapp.model.entity.Genre;
import com.example.movieapp.model.repo.GenreRepo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GenreActivity extends AppCompatActivity {

    private GenreAdapter adapter;
    private GenreRepo repo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        adapter = new GenreAdapter();
        repo = new GenreRepo(MainApplication.getDatabase(this).genreDao());

        RecyclerView recyclerView = findViewById(R.id.genreRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setOnAdapterItemClickListener(new GenreAdapter.OnAdapterItemClickListener() {
            @Override
            public void onClick(Genre genre) {
                Intent intent = new Intent(GenreActivity.this, EditGenreActivity.class);
                intent.putExtra(EditGenreActivity.KEY_GENRE_ID, genre.getId());
                startActivity(intent);
            }
        });

        FloatingActionButton fabGenre = findViewById(R.id.fabGenre);
        fabGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenreActivity.this, EditGenreActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.submitList(repo.findAll());
    }
}
