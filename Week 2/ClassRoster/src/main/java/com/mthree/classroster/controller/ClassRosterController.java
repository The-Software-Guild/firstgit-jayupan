/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.classroster.controller;

import com.mthree.classroster.dao.ClassRosterDao;
import com.mthree.classroster.dao.ClassRosterDaoFileImpl;
import com.mthree.classroster.dao.ClassRosterPersistenceException;
import com.mthree.classroster.dto.Student;
import com.mthree.classroster.service.ClassRosterDataValidationException;
import com.mthree.classroster.service.ClassRosterDuplicateIdException;
import com.mthree.classroster.service.ClassRosterService;
import com.mthree.classroster.ui.ClassRosterView;
import com.mthree.classroster.ui.UserIO;
import com.mthree.classroster.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Josef
 */
public class ClassRosterController {
    
    private UserIO io = new UserIOConsoleImpl();
    private ClassRosterView view;
    private ClassRosterService service;
    
    public ClassRosterController(ClassRosterService service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run()
    {
        boolean keepGoing = true;
        int menuSelection = 0; 
        
        while (keepGoing)
        {
            menuSelection = view.printMenuAndGetSelection();
            
            switch (menuSelection)
            {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }
    
    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        
        do {
            Student newStudent = view.getNewStudentInfo();
            try{
                service.createStudent(newStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            }
            catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
        
    }
    
    private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void viewStudent() {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }
    
    private void removeStudent() {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = service.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
    
}
