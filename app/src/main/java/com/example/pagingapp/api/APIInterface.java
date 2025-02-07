package com.example.pagingapp.api;

import com.example.pagingapp.model.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("movie/popular")
    Single<MovieResponse> getMoviesByPage(@Query("page") int page);
}
