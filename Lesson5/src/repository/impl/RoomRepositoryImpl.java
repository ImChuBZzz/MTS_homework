package repository.impl;

import entity.Room;
import repository.RoomRepository;

import java.util.HashSet;
import java.util.Set;

public class RoomRepositoryImpl implements RoomRepository {
    private static final Set<Room> rooms = new HashSet<>();


    @Override
    public Room save(Room room) {
        for(Room r: rooms) {
            String roomId = r.getId();
            if (roomId.equals(room.getId())) {
                rooms.remove(r);
                break;
            }
        }
        rooms.add(room);
        return room;
    }

    @Override
    public Set<Room> getAlL() {
        return rooms;
    }

    @Override
    public Room getBy(String id) {
        Room foundRoom = new Room();
        for(Room room: rooms) {
            if (room.getId().equals(id)) {
                foundRoom = room;
                break;
            }
        }
        return foundRoom;
    }

    @Override
    public void delete(Room room) {
        rooms.remove(room);
    }
}
