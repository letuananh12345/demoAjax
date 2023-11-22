package codegym.service;

import codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
    Page<Student> findAll(Pageable pageable);
    Page<Student> findByName(String name,Pageable pageable);

    Student findById(long id);

    Student save(Student student);

    void delete(Student student);
}
