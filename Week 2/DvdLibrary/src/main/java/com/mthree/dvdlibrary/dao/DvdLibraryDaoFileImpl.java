/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdlibrary.dao;

import com.mthree.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Josef
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao{
    private Map<String, Dvd> Dvds = new HashMap<>();
    
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException
    {
        loadLibrary();
        Dvd newDvd = Dvds.put(dvdId, dvd);
        writeLibrary();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException
    {
        loadLibrary();
        return new ArrayList<Dvd>(Dvds.values());
    }

    @Override
    public Dvd getDvd(String dvdId) throws DvdLibraryDaoException
    {
        loadLibrary();
        return Dvds.get(dvdId);
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryDaoException
    {
        loadLibrary();
        Dvd removedDvd = Dvds.remove(dvdId);
        writeLibrary();
        return removedDvd;    
    }
    
    @Override
    public Dvd editDvd(Dvd dvd) throws DvdLibraryDaoException
    {
        loadLibrary();
        Dvd editedDvd = Dvds.replace(dvd.getId(), dvd);
        writeLibrary();
        return editedDvd;
    }
    
    /**
     * Translates a line of text into a Dvd object
     * 
     * @param  dvdAsText object derived from one line of text
     * @return Dvd object created from line of text
     */
    private Dvd unmarshallDvd(String dvdAsText){
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1234::Jaws::1975::PG::Spielberg::Universal::They needed a bigger boat
        
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        String dvdId = dvdTokens[0];
        
        Dvd dvdFromFile = new Dvd(dvdId);

        // Index 1 - title
        dvdFromFile.setTitle(dvdTokens[1]);

        // Index 2 - date
        dvdFromFile.setDate(dvdTokens[2]);

        // Index 3 - rating
        dvdFromFile.setRating(dvdTokens[3]);
                
        // Index 4 - director
        dvdFromFile.setDirector(dvdTokens[4]);

        // Index 5 - studio
        dvdFromFile.setStudio(dvdTokens[5]);

        // Index 6 - note
        dvdFromFile.setNote(dvdTokens[6]);

        // We have now created a student! Return it!
        return dvdFromFile;
    }
    
    
    /**
     * Loads text file, breaks up each line into a Dvd object, and places it into a map
     */
    private void loadLibrary() throws DvdLibraryDaoException
    {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled
        Dvd currentDvd;
        // Go through LIBRARY_FILE line by line, decoding each line into a 
        // Dvd object by calling the unmarshallDvd method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a dvd
            currentDvd = unmarshallDvd(currentLine);

            // We are going to use the dvd id as the map key for our student object.
            // Put currentDvd into the map using dvd id as the key
            Dvds.put(currentDvd.getId(), currentDvd);
        }
        // close scanner
        scanner.close();
    }
    
    /**
     * Prepares the library to be written into a text file by converting it into a proper format
     * @param msg - String of information to display to the user.
     */
    private String marshallDvd(Dvd aDvd)
    {
        // We need to turn a Dvd object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 1234::Jaws::1975::PG::Spielberg::Universal::They needed a bigger boat

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the dvd id, since that's supposed to be first.
        String dvdAsText = aDvd.getId() + DELIMITER;

        // add the rest of the properties in the correct order:

        // title
        dvdAsText += aDvd.getTitle() + DELIMITER;

        // date
        dvdAsText += aDvd.getDate() + DELIMITER;

        // rating
        dvdAsText += aDvd.getRating() + DELIMITER;
        
        // director
        dvdAsText += aDvd.getDirector() + DELIMITER;

        // studio
        dvdAsText += aDvd.getStudio() + DELIMITER;

        // note
        dvdAsText += aDvd.getNote();

        // We have now turned a dvd to text! Return it!
        return dvdAsText;
    }
    
    /**
    * Writes all dvds in the library out to a LIBRARY_FILE.  See loadLibrary
    * for file format.
    * 
    * @throws DvdLibraryDaoException if an error occurs writing to the file
    */
   private void writeLibrary() throws DvdLibraryDaoException 
    {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save student data.", e);
        }

        // Write out the Dvd objects to the library file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of Dvds and iterate over them but we've
        // already created a method that gets a List of Dvds so
        // we'll reuse it.
        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            // turn a Student into a String
            dvdAsText = marshallDvd(currentDvd);
            // write the Student object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
