
package com.mobileprogramming.project.ygznsl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Movie implements Serializable, Comparable<Movie> {

    private String name, imgUrl, genre;
    private Calendar releaseDate;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format("%s\r\n%s\r\n%s", name, genre, new SimpleDateFormat("dd.MM.yyyy").format(releaseDate.getTime()));
    }

    @Override
    public int compareTo(Movie o) {
        return releaseDate.compareTo(o.getReleaseDate());
    }

}
