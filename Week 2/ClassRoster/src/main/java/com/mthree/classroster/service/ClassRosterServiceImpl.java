/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.classroster.service;

import com.mthree.classroster.dao.ClassRosterDao;
import com.mthree.classroster.dao.ClassRosterPersistenceException;
import com.mthree.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author Josef
 */
    public class ClassRosterServiceImpl implements
        ClassRosterService {
        
    ClassRosterDao dao;
   
    public ClassRosterServiceImpl(ClassRosterDao dao) {
        this.dao = dao;
    }
    
    @Override
public void createStudent(Student student) throws
        ClassRosterDuplicateIdException,
        ClassRosterDataValidationException,
        ClassRosterPersistenceException {
    
    // First check to see if there is alreay a student 
    // associated with the given student's id
    // If so, we're all done here - 
    // throw a ClassRosterDuplicateIdException
    if (dao.getStudent(student.getStudentId()) != null) {
        throw new ClassRosterDuplicateIdException(
                "ERROR: Could not create student.  Student Id "
                + student.getStudentId()
                + " already exists");
    }

    // Now validate all the fields on the given Student object.  
    // This method will throw an
    // exception if any of the validation rules are violated.
    validateStudentData(student);

    // We passed all our business rules checks so go ahead 
    // and persist the Student object
    dao.addStudent(student.getStudentId(), student);

}

    @Override
    public List<Student> getAllStudents() throws
            ClassRosterPersistenceException {
         return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws
            ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws
            ClassRosterPersistenceException {
        return dao.removeStudent(studentId);
    }
    
    private void validateStudentData(Student student) throws
        ClassRosterDataValidationException {

    if (student.getFirstName() == null
            || student.getFirstName().trim().length() == 0
            || student.getLastName() == null
            || student.getLastName().trim().length() == 0
            || student.getCohort() == null
            || student.getCohort().trim().length() == 0) {

        throw new ClassRosterDataValidationException(
                "ERROR: All fields [First Name, Last Name, Cohort] are required.");
    }
    }
}
