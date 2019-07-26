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
import com.example.movieapp.model.entity.Genre;

public class GenreAdapter extends ListAdapter<Genre, GenreAdapter.GenreViewHolder> {

    public interface OnAdapterItemClickListener {
        void onClick(Genre genre);
    }

    private static final DiffUtil.ItemCallback<Genre> DIFF_UTIL = new DiffUtil.ItemCallback<Genre>() {
        @Override
        public boolean areItemsTheSame(@NonNull Genre oldItem, @NonNull Genre newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Genre oldItem, @NonNull Genre newItem) {
            return oldItem.equals(newItem);
        }
    };

    private OnAdapterItemClickListener onAdapterItemClickListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    public GenreAdapter() {
        super(DIFF_UTIL);
    }


    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_genre, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Genre genre = getItem(position);
        holder.tvGenre.setText(genre.getGenre());
    }

    class GenreViewHolder extends RecyclerView.ViewHolder {

        final TextView tvGenre;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGenre = itemView.findViewById(R.id.tvGenre);

            itemView.setOnClickListener(v -> {
                if (onAdapterItemClickListener != null) {
                    onAdapterItemClickListener.onClick(getItem(getAdapterPosition()));
                }
            });
        }
    }
}
