package codegym.controller;

import codegym.model.ClassRoom;
import codegym.model.Student;
import codegym.service.ClassRoomService;
import codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    StudentService studentService;
    @Autowired
    ClassRoomService classRoomService;


    @ModelAttribute("classRoomss")
    public ArrayList<ClassRoom> classRoom() {
        return classRoomService.findAll();
    }


    @GetMapping("/home")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("listPage", studentService.findAll(PageRequest.of(page, 3, Sort.by("name"))));
        return modelAndView;
    }

    @GetMapping("/find")
    public ModelAndView findByName(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("listPage", studentService.findByName(name, PageRequest.of(0, 2, Sort.by("name"))));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("newStudent", new Student());
        return modelAndView;
    }

    @PostMapping("create")
//    **: phải hứng tham số của uppLoadFile để đầu tiên trong Post  create(), sau đó mới đến các biến sau (@Valid.....),
//       nếu không là lỗi . vì phải có ảnh lưu vào mới set và hứng đầy đủ @ModelAttribute đối tượng đc:
    public ModelAndView create(@RequestParam("uppAvatar") MultipartFile uppAvatar, @Valid @ModelAttribute("newStudent") Student student, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }

        String nameFileImg = uppAvatar.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppAvatar.getBytes(), new File("E:\\OntapMD4\\BT_Tong_Quat\\src\\main\\webapp\\fileUpload\\img\\" + nameFileImg));
            String urlAvatar = "/i/" + nameFileImg;
            student.setAvatar(urlAvatar);
        } catch (Exception e) {
            e.printStackTrace();
        }

        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id) {
        studentService.delete(studentService.findById(id));
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("studentEdit", studentService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@RequestParam("uppAvatar") MultipartFile uppAvatar, @Valid @ModelAttribute("studentEdit") Student student, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("edit");
            return modelAndView;
        }
        String nameFile = uppAvatar.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppAvatar.getBytes(), new File("E:\\OntapMD4\\BT_Tong_Quat\\src\\main\\webapp\\fileUpload\\img\\" + nameFile));
            String urlAvatar = "/i/" + nameFile;
            student.setAvatar(urlAvatar);
        } catch (Exception e) {
            e.printStackTrace();
        }

        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }
}
