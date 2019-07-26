package com.example.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.entity.Movie;

public class MovieAdapter extends ListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    public interface OnAdapterItemClickListener {
        void onClick(Movie movie);
    }

    private static final DiffUtil.ItemCallback<Movie> DIFF_UTIL = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    private OnAdapterItemClickListener onAdapterItemClickListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    public MovieAdapter() {
        super(DIFF_UTIL);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.tvMovie.setText(movie.getTitle());
        holder.tvGenre.setText(movie.getGenre().getGenre());
        holder.tvDuration.setText(movie.getDuration());
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        final TextView tvMovie;
        final TextView tvGenre;
        final TextView tvDuration;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMovie = itemView.findViewById(R.id.tvMovie);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            tvDuration = itemView.findViewById(R.id.tvDuration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAdapterItemClickListener != null) {
                        onAdapterItemClickListener.onClick(getItem(getAdapterPosition()));
                    }
                }
            });
        }
    }
}
