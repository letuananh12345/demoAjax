package codegym.controller;

import codegym.model.Student;
import codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students/api")
public class HomControllerAPI {
    @Autowired
    StudentService studentService;
    @GetMapping("/{name}")
    public ResponseEntity<Page<Student>> findByName(@PathVariable String name){
        return new ResponseEntity<>(studentService.findByName(name, PageRequest.of(0,2)), HttpStatus.OK);
    }
}
