package codegym.repository;

import codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IStudentRepository extends PagingAndSortingRepository<Student,Long> {
    Page<Student> findAllByNameContaining(String name, Pageable pageable);
}
