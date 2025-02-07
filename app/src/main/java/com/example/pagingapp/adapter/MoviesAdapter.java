package com.example.pagingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.pagingapp.databinding.SingleMovieItemBinding;
import com.example.pagingapp.model.Movie;
import com.example.pagingapp.viewmodel.MovieViewModel;

import io.reactivex.rxjava3.core.Single;
import kotlin.coroutines.CoroutineContext;

//6
public class MoviesAdapter extends PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int MOVIE_ITEM = 1;

    RequestManager glide;

    public MoviesAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(SingleMovieItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentMovie = getItem(position);

        if(currentMovie !=null){
            glide.load("https://image.tmdb.org/t/p/w500"+currentMovie.getPoster_path())
                    .into(holder.movieItemBinding.imageViewMovie);
        }
        holder.movieItemBinding.textViewRating.setText(String.valueOf(currentMovie.getVote_average()));

    }


    //The method getItemViewType is determining whether the current item at
    // position should display a movie item or a loading item based
    // on whether the position is at the end of the list.
    //It helps manage different view types within the same RecyclerView,
    // particularly useful for paginated or infinite scrolling lists where more data is loaded dynamically.
    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? MOVIE_ITEM : LOADING_ITEM;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        SingleMovieItemBinding movieItemBinding;
        public MovieViewHolder(@NonNull SingleMovieItemBinding movieItemBinding){
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }
    }

}
