package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.adapter.MovieCastAdapter;
import com.example.movieapp.model.repo.MovieCastRepo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CastActivity extends AppCompatActivity {

    private MovieCastAdapter adapter;
    private MovieCastRepo repo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);

        adapter = new MovieCastAdapter();
        repo = new MovieCastRepo(MainApplication.getDatabase(this).castDao());

        RecyclerView recyclerView = findViewById(R.id.castRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setOnAdapterItemClickListener(movieCast -> {
            Intent intent = new Intent(CastActivity.this, EditCastActivity.class);
            intent.putExtra(EditCastActivity.KEY_CAST_ID, movieCast.getId());
            startActivity(intent);
        });

        FloatingActionButton fab = findViewById(R.id.fabCast);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(CastActivity.this, EditCastActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.submitList(repo.findAll());
    }
}
