package com.example.pagingapp.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.pagingapp.model.Movie;
import com.example.pagingapp.paging.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

//7
public class MovieViewModel extends ViewModel {

    public Flowable<PagingData<Movie>> moviePagingDataFlowable;

    public MovieViewModel(){
        init();
    }

    private void init() {

        // Define Paging Source
        MoviePagingSource moviePagingSource = new MoviePagingSource();
        Pager<Integer, Movie> pager = new Pager<>(
                new PagingConfig(
                        20,    //count of items per page
                        20,              // number of items to prefetch
                        false,            //check of enabling placeholders for data which is not yet loaded
                        20,              // count of items to be loaded initially
                        20*449            // max count of items to be shown in recycler view
                ),
                ()-> moviePagingSource);

        // Flowable
        moviePagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(moviePagingDataFlowable, coroutineScope);


    }
}
