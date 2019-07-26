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
import com.example.movieapp.model.entity.MovieCast;

public class MovieCastAdapter extends ListAdapter<MovieCast, MovieCastAdapter.MovieCastViewHolder> {

    public interface OnAdapterItemClickListener {
        void onClick(MovieCast movieCast);
    }

    private static final DiffUtil.ItemCallback<MovieCast> DIFF_UTIL = new DiffUtil.ItemCallback<MovieCast>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieCast oldItem, @NonNull MovieCast newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieCast oldItem, @NonNull MovieCast newItem) {
            return oldItem.equals(newItem);
        }
    };

    private OnAdapterItemClickListener onAdapterItemClickListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    public MovieCastAdapter() {
        super(DIFF_UTIL);
    }

    @NonNull
    @Override
    public MovieCastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cast, parent, false);
        return new MovieCastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCastViewHolder holder, int position) {
        MovieCast movieCast = getItem(position);
        holder.tvCastName.setText(movieCast.getName());
        holder.tvDOB.setText(movieCast.getDateOfBirth().toString("yyyy/MM/dd"));
        holder.tvOccupation.setText(movieCast.getOccupation().name());
    }

    class MovieCastViewHolder extends RecyclerView.ViewHolder {

        final TextView tvCastName;
        final TextView tvDOB;
        final TextView tvOccupation;

        public MovieCastViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCastName = itemView.findViewById(R.id.tvCastName);
            tvDOB = itemView.findViewById(R.id.tvDOB);
            tvOccupation = itemView.findViewById(R.id.tvOccupation);

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
