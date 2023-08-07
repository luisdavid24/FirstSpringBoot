package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StundentRepository stundentRepository;

    @Autowired
    public StudentService(StundentRepository stundentRepository) {
        this.stundentRepository = stundentRepository;

    }

    public List<Student> getStudents(){
        return stundentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional=stundentRepository.findStudentByEmail((student.getEmail()));
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        stundentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists=stundentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id "+ studentId +" does not exists");
        }
        stundentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student= stundentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                        "student with id "+studentId+" does not exist "
                ));
        if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional=stundentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setName(email);

        }

    }
}
