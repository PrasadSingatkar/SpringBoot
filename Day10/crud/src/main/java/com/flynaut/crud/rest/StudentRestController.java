package com.flynaut.crud.rest;

import com.flynaut.crud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    //define @PostContruct to load data ... for once
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Krishna","Yadav"));
        theStudents.add(new Student("Gopal","Yadav"));
        theStudents.add(new Student("Govind","Yadav"));
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    //define an endpoint for "/students" - return the list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    // define an endpoint "/students/{studentId}"  ----- return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // check the studentId against list size
        if ((studentId > theStudents.size()) || (studentId<0)){
            throw new StudentNotFoundException("StudentId not found- "+studentId);
        }
        return theStudents.get(studentId);
    }
}
