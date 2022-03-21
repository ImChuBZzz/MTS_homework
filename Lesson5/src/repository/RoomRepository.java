package repository;

import entity.Room;
import java.util.Set;

public interface RoomRepository {
    Room save(Room room);
    Set<Room> getAlL();
    Room getBy(String id);
    void delete(Room room);
}
