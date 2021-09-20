/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdlibrary.ui;

import com.mthree.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author Josef
 */
public class DvdLibraryView {
    
    private UserIO io;
    
    public DvdLibraryView(UserIO io)
    {
        this.io = io;
    }
    
    /**
     * Prompts user for library functions
     * 
     * @return int for IO
     */
    public int printMenuAndGetSelection() 
    {
        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit a DVD");
        io.print("4. Lists DVDs");
        io.print("5. Display DVD by ID");
        io.print("6. Search DVD by title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    public Dvd getNewDvdInfo() 
    {
        String id = io.readString("Please enter an ID for DVD");
        String title = io.readString("Please enter DVD's title");
        String date = io.readString("Please enter DVD's release date");
        String rating = io.readString("Please enter DVD's MPAA rating");
        String director = io.readString("Please enter DVD's director");
        String studio = io.readString("Please enter DVD's production studio");
        String note = io.readString("Please provide a comment for DVD");
        Dvd currentDvd = new Dvd(id);
        currentDvd.setTitle(title);
        currentDvd.setDate(date);
        currentDvd.setRating(rating);
        currentDvd.setDirector(director);
        currentDvd.setStudio(studio);
        currentDvd.setNote(note);
        return currentDvd;
    }
    
    public void displayAddDvdBanner() 
    {
        io.print("=== Add DVD ===");
    }
    
    public void displayAddSuccessBanner() 
    {
        io.readString(
            "DVD successfully added.  Please hit enter to continue");
    }
    
    public void displayRemoveDvdBanner ()
    {
        io.print("=== Remove DVD ===");
    }
    
    public void displayRemoveResult(Dvd dvdRecord) 
    {
        if(dvdRecord != null){
          io.print(dvdRecord.getTitle() + " successfully removed.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayEditDvdBanner ()
    {
        io.print("=== Edit DVD ===");
    }
    
    public void displayEditResult (Dvd dvdRecord)
    {
        if(dvdRecord != null){
          io.print(dvdRecord.getTitle() + " successfully edited.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner() 
    {
        io.print("=== Display All DVDs ===");
    }
    
    public void displayDvdList(List<Dvd> dvdList) 
    {
        for (Dvd currentDvd : dvdList) {
            String dvdInfo = String.format("#%s : %s, %s, %s, %s, %s, '%s'",
                  currentDvd.getId(),
                  currentDvd.getTitle(),
                  currentDvd.getDate(),
                  currentDvd.getRating(),
                  currentDvd.getDirector(),
                  currentDvd.getStudio(),
                  currentDvd.getNote());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayDvdBanner () 
    {
        io.print("=== Display DVD ===");
    }

    public String getDvdIdChoice()
    {
        return io.readString("Please enter the DVD ID.");
    }
    
    public Dvd getEditDvdInfo(List<Dvd> dvdList, String dvdId) 
    {
        Dvd editDvd = new Dvd(dvdId);
        boolean isDvd = false;
        boolean isDone = false;
        for (Dvd currentDvd: dvdList) 
        {
            if(currentDvd.getId().equals(dvdId))
            {
                editDvd = currentDvd;
                isDvd = true;
            }
        }
        if(!isDvd) return null;
        while(!isDone)
        {
            String prompt = ("Edit Menu" + '\n' +
                "1. Change title: " + editDvd.getTitle() + "\n" +
                "2. Change release year: " + editDvd.getDate() + "\n" +
                "3. Change MPAA rating: " + editDvd.getRating() + "\n" +
                "4. Change director: " + editDvd.getDirector() + "\n" +
                "5. Change production studio: " + editDvd.getStudio() + "\n" +
                "6. Change comment: " + editDvd.getNote() + "\n" +
                "7. Exit");
            int menuSelection = io.readInt(prompt, 1, 7);
            switch (menuSelection)
            {
                case 1:
                    editDvd.setTitle(io.readString("Enter new title."));
                    break;
                case 2:
                    editDvd.setDate(io.readString("Enter new year."));
                    break;
                case 3:
                    editDvd.setRating(io.readString("Enter new MPAA rating."));
                    break;
                case 4:
                    editDvd.setDirector(io.readString("Enter new director."));
                    break;
                case 5:
                    editDvd.setStudio(io.readString("Enter new studio."));
                    break;
                case 6:
                    editDvd.setNote(io.readString("Enter new comment."));
                    break;
                case 7:
                    isDone = true;
                    break;
                default:
                    displayUnknownCommandBanner();
            }
        }
        return editDvd;
    }
    
    public void displayDvd(Dvd dvd) 
    {
        if (dvd != null) {
            io.print("ID: " + dvd.getId());
            io.print("Title: " + dvd.getTitle());
            io.print("Year: " + dvd.getDate());
            io.print("MPAA Rating: " + dvd.getRating());
            io.print("Director: " + dvd.getDirector());
            io.print("Studio: " + dvd.getStudio());
            io.print("Comment:" + dvd.getNote());
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayDvdTitleBanner () 
    {
        io.print("=== Display DVD by Title===");
    }
    
    public String getDvdTitleChoice(List<Dvd> dvdList)
    {
        String title = io.readString("Please enter the DVD title.");
        String id = "";
        for (Dvd currentDvd: dvdList) 
        {
            if(currentDvd.getTitle().equals(title)) id = currentDvd.getId();
        }
        return id;
    }

    public void displayExitBanner() 
    {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
