package repository;

import data.Room;

import java.sql.SQLException;
import java.util.List;


public interface RoomRepository {
    Room creatRoom(Room room) throws SQLException;
    Room updateRoom(Room room) throws SQLException;
    void deleteRoom(String id) throws SQLException;
    Room getRoomById(String id);
    List<Room> getRooms(String roomNumber, Integer floor, String roomType,Integer price);
}
