package com.example.pagingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serial;
import java.util.List;

//1
public class MovieResponse {

    @SerializedName("page")
    @Expose
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;

    @SerializedName("total_results")
    @Expose
    private Integer total_results;

    @SerializedName("total_pages")
    @Expose
    private Integer total_pages;

    public List<Movie> getMovies() {
        return movies;
    }
}
