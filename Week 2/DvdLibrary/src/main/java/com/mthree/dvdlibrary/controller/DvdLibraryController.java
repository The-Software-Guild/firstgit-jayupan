/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdlibrary.controller;

import com.mthree.dvdlibrary.dao.DvdLibraryDao;
import com.mthree.dvdlibrary.dao.DvdLibraryDaoException;
import com.mthree.dvdlibrary.dto.Dvd;
import com.mthree.dvdlibrary.ui.DvdLibraryView;
import com.mthree.dvdlibrary.ui.UserIO;
import com.mthree.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Josef
 */
public class DvdLibraryController {
    
    private UserIO io = new UserIOConsoleImpl();
    private DvdLibraryView view;
    private DvdLibraryDao dao;
    
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) 
    {
        this.dao = dao;
        this.view = view;
    }
    
    public void run()
    {
        boolean keepGoing = true;
        int menuSelection = 0; 
        
        try 
        {
            while (keepGoing)
            {
                menuSelection = view.printMenuAndGetSelection();

                switch (menuSelection)
                {
                    case 1:
                        addDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        listDvds();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
                        viewDvdTitle();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void addDvd() throws DvdLibraryDaoException
    {
        view.displayAddDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getId(), newDvd);
        view.displayAddSuccessBanner();
    }
    
    private void removeDvd() throws DvdLibraryDaoException
    {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd removedDvd = dao.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }
    
    private void editDvd() throws DvdLibraryDaoException
    {
        view.displayEditDvdBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        String dvdId = view.getDvdIdChoice();
        Dvd editedDvd = view.getEditDvdInfo(dvdList, dvdId);
        dao.editDvd(editedDvd);
        view.displayEditResult(editedDvd);
    }
    
    private void listDvds() throws DvdLibraryDaoException
    {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    
    private void viewDvd()  throws DvdLibraryDaoException
    {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getDvd(dvdId);
        view.displayDvd(dvd);
    }
    
    private void viewDvdTitle() throws DvdLibraryDaoException
    {
        view.displayDisplayDvdTitleBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        String dvdId = view.getDvdTitleChoice(dvdList);
        System.out.println(dvdId);
        Dvd dvd = dao.getDvd(dvdId);
        view.displayDvd(dvd);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
