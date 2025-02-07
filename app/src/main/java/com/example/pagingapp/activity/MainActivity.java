package com.example.pagingapp.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.example.pagingapp.R;
import com.example.pagingapp.adapter.MoviesAdapter;
import com.example.pagingapp.adapter.MoviesLoadStateAdapter;
import com.example.pagingapp.databinding.ActivityMainBinding;
import com.example.pagingapp.util.GridSpace;
import com.example.pagingapp.util.MovieComparator;
import com.example.pagingapp.util.Utils;
import com.example.pagingapp.viewmodel.MovieViewModel;
import androidx.databinding.DataBindingUtil;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    MovieViewModel mainActivityviewModel;
    ActivityMainBinding binding;
    MoviesAdapter moviesAdapter;


    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        if(Utils.API_KEY == null || Utils.API_KEY.isEmpty()){
            Toast.makeText(this,
                    "Error in API Key", Toast.LENGTH_SHORT).show();
        }
        moviesAdapter = new MoviesAdapter(new MovieComparator(), requestManager);

        mainActivityviewModel = new ViewModelProvider(this)
                .get(MovieViewModel.class);

        initRecyclerviewAndAdapter();

        // subscribe to paging (very important)
        mainActivityviewModel.moviePagingDataFlowable.subscribe(movingPagingData -> {
            moviesAdapter.submitData(getLifecycle(), movingPagingData);
        });
    }

    private void initRecyclerviewAndAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        binding.recyclerView.setLayoutManager(gridLayoutManager);

        binding.recyclerView.addItemDecoration(new GridSpace(2, 20, true));

        // showing progress bar which is used when pages(data)
        // are being fetched from the server
        binding.recyclerView.setAdapter(
                moviesAdapter.withLoadStateFooter(
                        new MoviesLoadStateAdapter(view -> {
                            moviesAdapter.retry();
                        })
                )
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM ? 1:1 ;
            }
        });

    }
}