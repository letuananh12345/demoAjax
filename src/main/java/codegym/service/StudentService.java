package codegym.service;

import codegym.model.Student;
import codegym.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {
    @Autowired
    IStudentRepository iStudentRepository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return iStudentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findByName(String name, Pageable pageable) {
        return iStudentRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Student findById(long id) {
        return iStudentRepository.findById(id).get();
    }

    @Override
    public Student save(Student student) {
        return iStudentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        iStudentRepository.delete(student);
    }
}
