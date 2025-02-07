package com.example.pagingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

//2
public class Movie {



    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    @SerializedName("vote_average")
    @Expose
    private double vote_average;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }


    //the equals() method is used to compare objects for equality,
    public boolean equals(Object o) {
        if (o == null) {
            return false;  // If the object passed is null, return false (they are not equal)
        } else if (o == this) {
            return true;  // If the object passed is the same instance as this object, return true (they are equal)
        }
        return false;  // In all other cases, return false (indicating the objects are not equal)
    }

}
