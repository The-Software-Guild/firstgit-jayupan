/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdlibrary;

import com.mthree.dvdlibrary.controller.DvdLibraryController;
import com.mthree.dvdlibrary.dao.DvdLibraryDao;
import com.mthree.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.mthree.dvdlibrary.ui.DvdLibraryView;
import com.mthree.dvdlibrary.ui.UserIO;
import com.mthree.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author Josef
 */
public class App {
    public static void main(String [] args)
    {
        UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller =
            new DvdLibraryController(myDao, myView);
        controller.run();

    }
}
