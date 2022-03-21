package service.impl;

import entity.Room;
import entity.RoomType;
import repository.RoomRepository;
import service.RoomService;
import service.exceptions.RequiredFieldMissedException;
import service.exceptions.RoomNotFoundException;

import java.util.*;

public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room getBy(String id) {

        if (roomRepository.getBy(id) == null) { throw new RoomNotFoundException(); }

        return roomRepository.getBy(id);
    }

    @Override
    public Room createRoom(Room room) {

        if(room.getRoomNumber().isBlank()
                || room.getFloor() == null
                || room.getPrice() == null
                || room.getType() == null) {
            throw new RequiredFieldMissedException();
        }

        room.setId(UUID.randomUUID().toString());

        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(String id, Room room) {

        if (roomRepository.getBy(id) != null) {
            roomRepository.save(room);
            room.setId(id);
            return roomRepository.getBy(id);
        }

        return null;
    }

    @Override
    public void deleteRoom(Room room) {
        if(roomRepository.getBy(room.getId()) != null) {
            roomRepository.delete(room);
        }
    }

    @Override
    public Map<RoomType, List<Room>> getRoomsGroupByType() {
        Map<RoomType, List<Room>> roomByType = new HashMap<>();

        Set<Room> rooms = roomRepository.getAlL();

        for(Room room: rooms){
            RoomType roomType = room.getType();
            if(roomByType.get(roomType) == null){
                roomByType.put(roomType, new ArrayList<>(List.of(room)));
            }
            else roomByType.get(roomType).add(room);
        }

        return roomByType;
    }
}

