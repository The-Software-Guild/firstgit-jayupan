/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdlibrary.dto;

/**
 *
 * @author Josef
 */
public class Dvd {
    private String id, title, date, rating, director, studio, note; // initialize variables
    
    public Dvd(String id)
    {
        this.id = id;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String getRating()
    {
        return rating;
    }
    
    public void setRating(String rating)
    {
        this.rating = rating;
    }
    
    public String getDirector()
    {
        return director;
    }
    
    public void setDirector(String director)
    {
        this.director = director;
    }
    
    public String getStudio()
    {
        return studio;
    }
    
    public void setStudio(String studio)
    {
        this.studio = studio;
    }
    
    public String getNote()
    {
        return note;
    }
    
    public void setNote(String note)
    {
        this.note = note;
    }
}
