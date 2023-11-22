package codegym.repository;

import codegym.model.ClassRoom;
import org.springframework.data.repository.CrudRepository;

public interface IClassRoomRepository extends CrudRepository<ClassRoom,Long> {
}
