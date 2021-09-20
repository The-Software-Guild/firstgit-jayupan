/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdlibrary.dao;

import com.mthree.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface DvdLibraryDao {
    /**
     * Adds the given DVD to the library and associates it with the given
     * DVD id. If there is already a DVD associated with the given
     * DVD id it will return that Dvd object, otherwise it will
     * return null.
     *
     * @param dvdID id with which dvd is to be associated
     * @param dvd dvd to be added to the library
     * @return the Dvd object previously associated with the given  
     * DVD id if it exists, null otherwise
     */
    Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException;

    /**
     * Returns a List of all DVDs in the library.
     *
     * @return List containing all DVDs in the library.
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    /**
     * Returns the DVD object associated with the given DVD id.
     * Returns null if no such DVD exists
     *
     * @param dvdId ID of the DVD to retrieve
     * @return the Dvd object associated with the given Dvd id,  
     * null if no such student exists
     */
    Dvd getDvd(String dvdId) throws DvdLibraryDaoException;

    /**
     * Removes from the roster the Dvd associated with the given id.
     * Returns the DVD object that is being removed or null if
     * there is no DVD associated with the given id
     *
     * @param dvdId id of student to be removed
     * @return Dvd object that was removed or null if no DVD
     * was associated with the given DVD id
     */
    Dvd removeDvd(String dvdId) throws DvdLibraryDaoException;
    
    /**
     * Edits from the roster the Dvd associated with the given id.
     * Returns the DVD object that is being edited or null if
     * there is no DVD associated with the given id
     * 
     * @param dvd edited dvd to be replaced in the library
     * @return Dvd object that was edited or null if no DVD
     * was associated with the given DVD id
     */
    Dvd editDvd(Dvd dvd) throws DvdLibraryDaoException;
    

}
