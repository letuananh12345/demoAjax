package codegym.service;

import codegym.model.ClassRoom;

import java.util.ArrayList;

public interface IClassRoomService {
    ArrayList<ClassRoom> findAll();

    ClassRoom findById(long id);
}
